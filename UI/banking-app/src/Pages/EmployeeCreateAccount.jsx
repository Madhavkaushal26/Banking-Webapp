import React, { useState } from "react";
import axiosInstance from "../Services/axiosInstance";

const EmployeeCreateAccount = () => {
    const [customerId, setCustomerId] = useState("");
    const [accountType, setAccountType] = useState("SAVINGS");
    const [balance, setBalance] = useState("");
    const [nomineeId, setNomineeId] = useState("");
    const [pin, setPin] = useState("");
    const [pass, setPass] = useState("");
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async () => {
        if (!customerId || !accountType || !balance || !nomineeId || !pin || !pass) {
            setError("All fields are required.");
            setMessage("");
            return;
        }

        const requestData = {
            customerId: customerId,
            accountType: accountType,
            balance: parseFloat(balance),
            nomi_id: parseInt(nomineeId),
            pin: pin,
            pass: pass,
        };

        try {
            await axiosInstance.post("/api/accounts/create", requestData);
            setMessage("Account created successfully.");
            setError("");
        } catch (err) {
            setError(
                err.response?.data?.message || "Account creation failed. Please try again."
            );
            setMessage("");
        }
    };

    return (
        <div className="p-4">
            <h2>Create Customer Account</h2>
            {message && <p style={{ color: "green" }}>{message}</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            <div>
                <label>Customer ID:</label>
                <input
                    type="text"
                    value={customerId}
                    onChange={(e) => setCustomerId(e.target.value)}
                />
            </div>

            <div>
                <label>Account Type:</label>
                <select value={accountType} onChange={(e) => setAccountType(e.target.value)}>
                    <option value="SAVINGS">SAVINGS</option>
                    <option value="CURRENT">CURRENT</option>
                </select>
            </div>

            <div>
                <label>Balance:</label>
                <input
                    type="number"
                    value={balance}
                    onChange={(e) => setBalance(e.target.value)}
                />
            </div>

            <div>
                <label>Nominee ID:</label>
                <input
                    type="number"
                    value={nomineeId}
                    onChange={(e) => setNomineeId(e.target.value)}
                />
            </div>

            <div>
                <label>PIN:</label>
                <input
                    type="password"
                    value={pin}
                    onChange={(e) => setPin(e.target.value)}
                />
            </div>

            <div>
                <label>Password:</label>
                <input
                    type="password"
                    value={pass}
                    onChange={(e) => setPass(e.target.value)}
                />
            </div>

            <button onClick={handleSubmit}>Create Account</button>
        </div>
    );
};

export default EmployeeCreateAccount;
