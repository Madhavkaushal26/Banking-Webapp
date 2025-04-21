import React, { useState } from "react";
import axiosInstance from "../services/axiosInstance";

const AddCustomer = () => {
    const [formData, setFormData] = useState({
        fullName: "",
        dob: "",
        gender: "",
        nationality: "",
        password: "",
        email: "",
        phNo: "",
        address: ""
    });

    const [success, setSuccess] = useState("");
    const [error, setError] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const requestBody = {
            fullName: formData.fullName,
            dob: formData.dob,
            gender: formData.gender,
            nationality: formData.nationality,
            password: formData.password,
            contacts: [
                {
                    email: formData.email,
                    phNo: formData.phNo,
                    address: formData.address
                }
            ]
        };

        axiosInstance
            .post("/api/customers/register", requestBody)
            .then(() => {
                setSuccess("Customer registered successfully!");
                setError("");
                setFormData({
                    fullName: "",
                    dob: "",
                    gender: "",
                    nationality: "",
                    password: "",
                    email: "",
                    phNo: "",
                    address: ""
                });
            })
            .catch(() => {
                setError("Failed to register customer.");
                setSuccess("");
            });
    };

    return (
        <div className="add-customer-form">
            <h2>Add New Customer</h2>
            {error && <div className="error">{error}</div>}
            {success && <div className="success">{success}</div>}

            <form onSubmit={handleSubmit}>
                <input type="text" name="fullName" placeholder="Full Name" value={formData.fullName} onChange={handleChange} required />
                <input type="date" name="dob" placeholder="Date of Birth" value={formData.dob} onChange={handleChange} required />
                <input type="text" name="gender" placeholder="Gender" value={formData.gender} onChange={handleChange} required />
                <input type="text" name="nationality" placeholder="Nationality" value={formData.nationality} onChange={handleChange} required />
                <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />

                <hr />

                <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
                <input type="text" name="phNo" placeholder="Phone Number" value={formData.phNo} onChange={handleChange} required />
                <input type="text" name="address" placeholder="Address" value={formData.address} onChange={handleChange} required />

                <button type="submit">Register Customer</button>
            </form>
        </div>
    );
};

export default AddCustomer;
