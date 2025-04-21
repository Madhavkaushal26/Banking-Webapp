import React, { useState } from "react";
import axiosInstance from "../Services/axiosInstance";

const EmployeeTransactions = () => {
    const [customerId, setCustomerId] = useState("");
    const [accounts, setAccounts] = useState([]);
    const [selectedAccount, setSelectedAccount] = useState("");
    const [transactions, setTransactions] = useState([]);
    const [step, setStep] = useState(1);
    const [error, setError] = useState("");

    const handleCustomerSubmit = async () => {
        try {
            const response = await axiosInstance.get(`/api/accounts/customer/${customerId}`);
            setAccounts(response.data);
            setStep(2);
            setError("");
        } catch (err) {
            setError("Failed to fetch accounts. Make sure Customer ID is correct.");
        }
    };

    const handleAccountSubmit = async () => {
        try {
            const response = await axiosInstance.get(`/api/transactions/account/${selectedAccount}`);
            setTransactions(response.data);
            setStep(3);
            setError("");
        } catch (err) {
            setError("Failed to fetch transactions.");
        }
    };

    // Format the timestamp (YYYY-MM-DD HH:mm:ss)
    const formatTimestamp = (timestamp) => {
        const date = new Date(timestamp);
        const formattedDate = date.toLocaleDateString(); // Formats the date
        const formattedTime = date.toLocaleTimeString(); // Formats the time
        return { formattedDate, formattedTime };
    };

    return (
        <div>
            <h2>View Customer Transactions</h2>

            {error && <div style={{ color: "red" }}>{error}</div>}

            {step === 1 && (
                <div>
                    <label>Enter Customer ID: </label>
                    <input
                        type="text"
                        value={customerId}
                        onChange={(e) => setCustomerId(e.target.value)}
                    />
                    <button onClick={handleCustomerSubmit}>Submit</button>
                </div>
            )}

            {step === 2 && (
                <div>
                    <label>Select Account:</label>
                    <select
                        value={selectedAccount}
                        onChange={(e) => setSelectedAccount(e.target.value)}
                    >
                        <option value="">Choose account</option>
                        {accounts.map((acc) => (
                            <option key={acc.accountNumber} value={acc.accountNumber}>
                                {acc.accountNumber} - Balance: {acc.balance}
                            </option>
                        ))}
                    </select>
                    <button onClick={handleAccountSubmit}>View Transactions</button>
                </div>
            )}

            {step === 3 && (
                <div>
                    <h3>Transactions for Account {selectedAccount}</h3>
                    {transactions.length === 0 ? (
                        <p>No transactions found.</p>
                    ) : (
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Amount</th>
                                    <th>Date</th>
                                    <th>Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                {transactions.slice().reverse().map((tx) => {
                                    const { formattedDate, formattedTime } = formatTimestamp(tx.transactionDateTime);
                                    return (
                                        <tr key={tx.id}>
                                            <td>{tx.id}</td>
                                            <td>{tx.fromAccount?.accountNumber || tx.fromAccount}</td>
                                            <td>{tx.toAccount?.accountNumber || tx.toAccount}</td>
                                            <td>{tx.amount}</td>
                                            <td>{formattedDate}</td>
                                            <td>{formattedTime}</td>
                                        </tr>
                                    );
                                })}
                            </tbody>

                        </table>
                    )}
                </div>
            )}
        </div>
    );
};

export default EmployeeTransactions;
