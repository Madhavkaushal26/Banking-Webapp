import React, { useState, useEffect } from "react";
import axiosInstance from '../Services/axiosInstance';

const PaymentPage = () => {
    const [accounts, setAccounts] = useState([]);
    const [senderAccount, setSenderAccount] = useState("");
    const [receiverAccount, setReceiverAccount] = useState("");
    const [amount, setAmount] = useState("");
    const [pin, setPin] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const [customerId, setCustomerId] = useState(() => {
        const user = JSON.parse(localStorage.getItem('user')); // Get the 'user' object from localStorage
        return user ? user.username : ''; // Default to empty string for customer if no user found
    });
    // Fetch accounts for the logged-in user (assuming an API endpoint exists)
    useEffect(() => {
        axiosInstance.get(`/api/accounts/customer/${customerId}`)
            // Adjust the API call based on your actual endpoint
            .then((response) => {
                setAccounts(response.data);
            })
            .catch((error) => {
                setError("Failed to load accounts");
            });
    }, []);

    const handlePayment = () => {
        if (!senderAccount || !receiverAccount || !amount || !pin) {
            setError("All fields are required.");
            return;
        }

        // Construct the request body (in the correct format)
        const paymentData = {
            senderAccountNumber: senderAccount,
            receiverAccountNumber: receiverAccount,
            amount: parseFloat(amount),
            pin,
        };

        // Make the payment request (POST to the correct API URL)
        axiosInstance.post("/api/transactions", paymentData)
            .then((response) => {
                setSuccess("Payment successful!");
                setError("");
            })
            .catch((error) => {
                setError("Payment failed. Please check your details.");
                setSuccess("");
            });
    };

    return (
        <div className="payment-page">
            <h2>Make a Payment</h2>

            {error && <div className="error">{error}</div>}
            {success && <div className="success">{success}</div>}

            <div>
                <label htmlFor="senderAccount">From Account:</label>
                <select
                    id="senderAccount"
                    value={senderAccount}
                    onChange={(e) => setSenderAccount(e.target.value)}
                >
                    <option value="">Select Account</option>
                    {Array.isArray(accounts) && accounts.map((account) => (
                        <option key={account.accountNumber} value={account.accountNumber}>
                            {account.accountNumber} - Balance: {account.balance}
                        </option>
                    ))}

                </select>
            </div>

            <div>
                <label htmlFor="receiverAccount">To Account:</label>
                <input
                    type="text"
                    id="receiverAccount"
                    value={receiverAccount}
                    onChange={(e) => setReceiverAccount(e.target.value)}
                    placeholder="Enter Receiver Account Number"
                />
            </div>

            <div>
                <label htmlFor="amount">Amount:</label>
                <input
                    type="number"
                    id="amount"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    placeholder="Enter Amount"
                />
            </div>

            <div>
                <label htmlFor="pin">PIN:</label>
                <input
                    type="password"
                    id="pin"
                    value={pin}
                    onChange={(e) => setPin(e.target.value)}
                    placeholder="Enter your PIN"
                />
            </div>

            <button onClick={handlePayment}>Make Payment</button>
        </div>
    );
};

export default PaymentPage;
