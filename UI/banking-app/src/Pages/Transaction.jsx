import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axiosInstance from '../Services/axiosInstance'; // Import axios instance
import './Css/Transaction.css';

const Transaction = () => {
    const navigate = useNavigate();
    const { id } = useParams(); // This will be used in the employee view to capture customer ID

    const [customerId, setCustomerId] = useState(() => {
        const user = JSON.parse(localStorage.getItem('user')); // Get the 'user' object from localStorage
        return user ? user.username : ''; // Default to empty string for customer if no user found
    });

    const [accounts, setAccounts] = useState([]); // Store customer accounts
    const [selectedAccount, setSelectedAccount] = useState(null); // Store selected account
    const [transactions, setTransactions] = useState([]); // Store transactions
    const [isEmployeeView, setIsEmployeeView] = useState(Boolean(id)); // If 'id' is provided, it's employee view

    // Fetch accounts for the customer or employee (depending on the context)
    useEffect(() => {
        // If no customerId or id, redirect to login
        if (!customerId && !id) {
            console.log("Redirecting to login due to missing customerId or id");
            navigate('/login');
            return;
        }

        // If employee, fetch data based on id. If customer, fetch based on customerId
        const fetchAccounts = async () => {
            try {
                const response = await axiosInstance.get(`/api/accounts/customer/${id || customerId}`);
                setAccounts(response.data);
            } catch (error) {
                console.error('Error fetching accounts:', error);
            }
        };

        fetchAccounts();
    }, [customerId, id, navigate]); // Re-run effect on changes to customerId or id

    // Fetch transactions for the selected account
    const fetchTransactions = async (accountId) => {
        try {
            const response = await axiosInstance.get(`/api/transactions/account/${accountId}`);
            setTransactions(response.data);
        } catch (error) {
            console.error('Error fetching transactions:', error);
        }
    };

    // Handle account selection for customer
    const handleAccountChange = (e) => {
        const accountId = e.target.value;
        setSelectedAccount(accountId);
        fetchTransactions(accountId);
    };

    // Handle customer ID input for employee view
    const handleCustomerIdSubmit = (e) => {
        e.preventDefault();
        navigate(`/employee/transaction/${customerId}`); // Redirect to employee transaction page with customerId
    };

    // Format the timestamp (YYYY-MM-DD HH:mm:ss)
    const formatTimestamp = (timestamp) => {
        const date = new Date(timestamp);
        const formattedDate = date.toLocaleDateString(); // Formats the date
        const formattedTime = date.toLocaleTimeString(); // Formats the time
        return { formattedDate, formattedTime };
    };

    return (
        <div className="transaction-container">
            <div className="transaction-content">
                {isEmployeeView ? (
                    <>
                        <h2>Employee Transaction Page</h2>
                        <form className="form-row" onSubmit={handleCustomerIdSubmit}>
                            <label htmlFor="customer-id">Enter Customer ID:</label>
                            <input
                                id="customer-id"
                                type="text"
                                value={customerId || ''}
                                onChange={(e) => setCustomerId(e.target.value)}
                                placeholder="Enter customer ID"
                            />
                            <button type="submit">Submit</button>
                        </form>
                    </>
                ) : (
                    <h2>Customer Transaction Page</h2>
                )}

                <div className="account-select-row">
                    <label htmlFor="account-select">Select Account:</label>
                    <select
                        id="account-select"
                        onChange={handleAccountChange}
                        value={selectedAccount}
                    >
                        <option value="">Select an account</option>
                        {accounts.map((account) => (
                            <option key={account.accountNumber} value={account.accountNumber}>
                                {account.accountNumber}
                            </option>
                        ))}
                    </select>
                </div>

                {selectedAccount && (
                    <>
                        <h3>Transactions for Account: {selectedAccount}</h3>
                        {transactions.length > 0 ? (
                            <div className="table-wrapper">
                                <table className="transaction-table">
                                    <thead>
                                        <tr>
                                            <th>Transaction ID</th>
                                            <th>Sender</th>
                                            <th>Receiver</th>
                                            <th>Amount</th>
                                            <th>Date</th>
                                            <th>Time</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {transactions.map((transaction) => {
                                            const { formattedDate, formattedTime } = formatTimestamp(transaction.transactionDateTime);
                                            return (
                                                <tr key={transaction.id}>
                                                    <td>{transaction.id}</td>
                                                    <td>{transaction.fromAccount.accountNumber}</td>
                                                    <td>{transaction.toAccount.accountNumber}</td>
                                                    <td>{transaction.amount}</td>
                                                    <td>{formattedDate}</td>
                                                    <td>{formattedTime}</td>
                                                </tr>
                                            );
                                        })}
                                    </tbody>
                                </table>
                            </div>
                        ) : (
                            <p>No transactions found for this account.</p>
                        )}
                    </>
                )}
            </div>
        </div>
    );
};

export default Transaction;
