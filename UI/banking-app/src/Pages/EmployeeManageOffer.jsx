import React, { useEffect, useState } from 'react';
import axiosInstance from '../Services/axiosInstance';

const EmployeeManageOffersPage = () => {
    const [allOffers, setAllOffers] = useState([]);
    const [eligibleOffers, setEligibleOffers] = useState([]);
    const [displayedOffers, setDisplayedOffers] = useState([]);
    const [customerId, setCustomerId] = useState('');
    const [message, setMessage] = useState('');
    const [showAll, setShowAll] = useState(false);

    useEffect(() => {
        if (customerId) {
            Promise.all([
                axiosInstance.get('/api/offers/all'),
                axiosInstance.get(`/api/offers/eligible/${customerId}`)
            ])
                .then(([allRes, eligibleRes]) => {
                    const all = allRes.data;
                    const eligible = eligibleRes.data;
                    setAllOffers(all);
                    setEligibleOffers(eligible.map(o => o.offerCode));
                    setDisplayedOffers(showAll ? all : eligible);
                })
                .catch(() => {
                    setMessage("Failed to load offers.");
                    setDisplayedOffers([]);
                });
        }
    }, [customerId, showAll]);

    const handleAvail = (offerCode) => {
        axiosInstance.post('/api/customer-offers/avail', {
            customerId,
            offerCode
        })
            .then(() => {
                setMessage(`Offer ${offerCode} availed successfully!`);
                // Refresh eligible offers list after availing
                axiosInstance.get(`/api/offers/eligible/${customerId}`)
                    .then((res) => {
                        setEligibleOffers(res.data.map(o => o.offerCode));
                        if (!showAll) {
                            setDisplayedOffers(res.data);
                        }
                    });
            })
            .catch(() => {
                setMessage(`Failed to avail offer ${offerCode}.`);
            });
    };

    const isOfferEligible = (offerCode) => eligibleOffers.includes(offerCode);

    return (
        <div className="offers-page">
            <h2>{showAll ? "All Offers in System" : "Eligible Offers for Customer"}</h2>

            <div style={{ marginBottom: "1rem" }}>
                <label>Enter Customer ID: </label>
                <input
                    type="text"
                    value={customerId}
                    onChange={(e) => setCustomerId(e.target.value)}
                    placeholder="Customer ID"
                />
            </div>

            <button onClick={() => setShowAll(!showAll)}>
                {showAll ? "Show Eligible Only" : "Show All Offers"}
            </button>

            {message && <p>{message}</p>}

            {displayedOffers.length === 0 ? (
                <p>No offers to display.</p>
            ) : (
                <ul>
                    {displayedOffers.map((offer) => (
                        <li key={offer.offerCode}>
                            <strong>{offer.offerName}</strong> - {offer.description}
                            <button
                                onClick={() => handleAvail(offer.offerCode)}
                                disabled={!isOfferEligible(offer.offerCode)}
                                style={{
                                    marginLeft: "10px",
                                    opacity: isOfferEligible(offer.offerCode) ? 1 : 0.5,
                                    cursor: isOfferEligible(offer.offerCode) ? 'pointer' : 'not-allowed'
                                }}
                            >
                                Avail
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default EmployeeManageOffersPage;
