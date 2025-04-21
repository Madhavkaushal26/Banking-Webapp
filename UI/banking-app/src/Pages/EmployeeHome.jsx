import React from 'react';
import { Link } from 'react-router-dom';

function EmployeeHome() {
    return (
        <div className="employee-home">
            <h1>Welcome to the Employee Dashboard</h1>
            <p>As an employee, you can add customers, view transactions, and manage offers.</p>

            <div className="actions" style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
                <Link to="/employee/AddCustomer">
                    <button>Add Customers</button>
                </Link>
                <Link to="/employee/CustomerKYC">
                    <button>Customer KYC</button>
                </Link>
                <Link to="/employee/transactions">
                    <button>View Transactions</button>
                </Link>
                <Link to="/employee/Accounts">
                    <button>Create New Account</button>
                </Link>
                <Link to="/employee/Offers">
                    <button>Manage Offers</button>
                </Link>
            </div>

        </div>
    );
}

export default EmployeeHome;
