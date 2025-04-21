// CustomerHome.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';

const CustomerHome = () => {
    const navigate = useNavigate();

    return (
        <div className="min-h-screen bg-gray-900 text-white p-6">
            <h1 className="text-3xl font-bold mb-6 text-center">Customer Home</h1>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-3xl mx-auto">
                <button
                    onClick={() => navigate('/Payment')}
                    className="bg-blue-600 hover:bg-blue-700 py-3 px-6 rounded-2xl shadow-md"
                >
                    Make a Payment
                </button>
                <button
                    onClick={() => navigate('/customer/transaction')}
                    className="bg-green-600 hover:bg-green-700 py-3 px-6 rounded-2xl shadow-md"
                >
                    View My Transactions
                </button>
                <button
                    onClick={() => navigate('/offers')}
                    className="bg-yellow-600 hover:bg-yellow-700 py-3 px-6 rounded-2xl shadow-md"
                >
                    Offers I Can Avail
                </button>
                <button
                    onClick={() => navigate('/offersAvailed')}
                    className="bg-purple-600 hover:bg-purple-700 py-3 px-6 rounded-2xl shadow-md"
                >
                    Offers I Have Availed
                </button>
            </div>
        </div>
    );
};

export default CustomerHome;
