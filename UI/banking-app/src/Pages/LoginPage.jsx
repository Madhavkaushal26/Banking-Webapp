import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../Services/axiosInstance';
import './Css/LoginPage.css';

const LoginPage = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '' });
    const [isEmployee, setIsEmployee] = useState(true);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!credentials.username || !credentials.password) {
            alert('Username and password are required.');
            return;
        }

        const apiUrl = isEmployee
            ? 'http://localhost:8080/auth/employee/login'
            : 'http://localhost:8080/auth/customer/login';

        try {
            const response = await axiosInstance.post(apiUrl, credentials);

            if (response.status === 200) {
                const userInfo = response.data;
                localStorage.setItem('user', JSON.stringify(userInfo));

                if (userInfo.role === 'employee') {
                    navigate('/employee/home');
                } else {
                    navigate('/customer/home');
                }
            }
        } catch (error) {
            // Reset password field on failure
            setCredentials((prev) => ({ ...prev, password: '' }));

            if (error.response) {
                if (error.response.status === 401 || error.response.status === 404) {
                    alert('Invalid credentials. Please try again.');
                } else {
                    alert('An unexpected error occurred. Please try again later.');
                }
            } else {
                alert('Network error. Please check your internet connection.');
            }
        }
    };


    return (
        <div className="login-container">
            <h2 className="login-title">Login Page</h2>
            <form onSubmit={handleSubmit} className="login-form">
                <div className="form-group">
                    <label>Username:
                        <input
                            type="text"
                            name="username"
                            value={credentials.username}
                            onChange={handleChange}
                            required
                            className="form-input"
                        />
                    </label>
                </div>
                <div className="form-group">
                    <label>Password:
                        <input
                            type="password"
                            name="password"
                            value={credentials.password}
                            onChange={handleChange}
                            required
                            className="form-input"
                        />
                    </label>
                </div>
                <div className="form-group radio-group">
                    <label>
                        <input
                            type="radio"
                            checked={!isEmployee}
                            onChange={() => setIsEmployee(false)}
                        />
                        Customer Login
                    </label>
                    <label>
                        <input
                            type="radio"
                            checked={isEmployee}
                            onChange={() => setIsEmployee(true)}
                        />
                        Employee Login
                    </label>
                </div>
                <button type="submit" className="login-button">Login</button>
            </form>
        </div>
    );
};

export default LoginPage;
