import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../Services/axiosInstance'; // Import axios instance
import "./LoginForm.css"

function LoginForm() {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [isEmployee, setIsEmployee] = useState(false); // Default is customer login
    const [error, setError] = useState(''); // For storing error messages
    const navigate = useNavigate(); // Initialize useNavigate hook for redirection

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Ensure both fields are filled
        if (!credentials.username || !credentials.password) {
            setError('Username and password are required.');
            return;
        }

        const apiUrl = isEmployee
            ? 'http://localhost:8080/auth/employee/login'
            : 'http://localhost:8080/auth/customer/login';

        try {
            const response = await axiosInstance.post(apiUrl, credentials);

            if (response.status === 200) {
                const userInfo = response.data;
                console.log('Login successful');
                localStorage.setItem('user', JSON.stringify(userInfo)); // Store user info in localStorage

                // Redirect to the correct home page based on login type
                if (isEmployee) {
                    navigate('/employee/home'); // Redirect to employee home page
                } else {
                    navigate('/customer/home'); // Redirect to customer home page
                }
            } else {
                setError('Invalid credentials'); // Set error message if login fails
            }
        } catch (error) {
            console.error('Login error:', error);
            setError('Error during login. Please try again later.');
        }
    };

    return (
        <div>
            <h2>Inclusive Banking - Login</h2>

            {/* Display error message if there's any */}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input
                        type="text"
                        value={credentials.username}
                        onChange={(e) => setCredentials({ ...credentials, username: e.target.value })}
                    />
                </label>
                <br />
                <label>
                    Password:
                    <input
                        type="password"
                        value={credentials.password}
                        onChange={(e) => setCredentials({ ...credentials, password: e.target.value })}
                    />
                </label>
                <br />

                {/* Toggle between Employee and Customer login */}
                <div>
                    <label>
                        <input
                            type="radio"
                            checked={!isEmployee} // Customer login is checked by default
                            onChange={() => setIsEmployee(false)} // Set to customer login
                        />
                        Customer Login
                    </label>
                    <label>
                        <input
                            type="radio"
                            checked={isEmployee} // Employee login is unchecked by default
                            onChange={() => setIsEmployee(true)} // Set to employee login
                        />
                        Employee Login
                    </label>
                </div>

                <br />
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default LoginForm;
