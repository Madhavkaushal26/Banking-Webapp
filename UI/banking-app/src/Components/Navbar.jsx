import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem("user");
        navigate("/login");
    };

    if (!user) return null;

    return (
        <nav className="navbar">
            <div className="navbar-brand">Banking App</div>
            <div className="navbar-links">
                {user.role === "EMPLOYEE" ? (
                    <>
                        <Link to="/employee">Home</Link>
                        <Link to="/employee/AddCustomer">Add Customer</Link>
                        <Link to="/employee/CustomerKYC">KYC</Link>
                        <Link to="/employee/Accounts">Create Account</Link>
                        <Link to="/employee/transactions">Transactions</Link>
                        <Link to="/employee/Offers">Manage Offers</Link>
                    </>
                ) : (
                    <>
                        <Link to="/customer/home">Home</Link>
                        <Link to="/payment">Payment</Link>
                        <Link to="/customer/transaction">Transactions</Link>
                        <Link to="/offers">Available Offers</Link>
                        <Link to="/offersAvailed">Availed Offers</Link>
                    </>
                )}
                <button className="logout-btn" onClick={handleLogout}>Logout</button>
            </div>
        </nav>
    );
};

export default Navbar;
