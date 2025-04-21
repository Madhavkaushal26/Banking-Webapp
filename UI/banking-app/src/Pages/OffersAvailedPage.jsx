import React, { useEffect, useState } from "react";
import axiosInstance from "../Services/axiosInstance";

const OffersAvailedPage = () => {
    const [offers, setOffers] = useState([]);
    const [error, setError] = useState("");

    const customerId = JSON.parse(localStorage.getItem("user"))?.username;

    useEffect(() => {
        if (customerId) {
            axiosInstance
                .get(`/api/customer-offers/availed/${customerId}`)
                .then((response) => {
                    setOffers(response.data);
                })
                .catch(() => {
                    setError("Failed to load availed offers.");
                });
        } else {
            setError("Customer not logged in.");
        }
    }, [customerId]);

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Offers You've Availed</h2>
            {error && <p className="text-red-500">{error}</p>}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {offers.map((offer) => (
                    <div key={offer.offerCode} className="border p-4 rounded shadow">
                        <h3 className="font-semibold text-lg">{offer.offerName}</h3>
                        <p className="text-sm text-gray-600">Code: {offer.offerCode}</p>
                        <p>{offer.description}</p>
                    </div>
                ))}
                {offers.length === 0 && !error && <p>No offers availed yet.</p>}
            </div>
        </div>
    );
};

export default OffersAvailedPage;
