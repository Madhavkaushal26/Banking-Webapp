    import React, { useEffect, useState } from 'react';
    import axiosInstance from '../Services/axiosInstance';

    const OffersAvailablePage = () => {
        const [offers, setOffers] = useState([]);
        const [availableOffers, setAvailableOffers] = useState([]);
        const [showAll, setShowAll] = useState(false);
        const [message, setMessage] = useState('');

        const [customerId, setCustomerId] = useState(() => {
            const user = JSON.parse(localStorage.getItem('user'));
            return user ? user.username : '';
        });

        useEffect(() => {
            if (showAll) {
                // Fetch all offers and available offers
                Promise.all([
                    axiosInstance.get('/api/offers/all'),
                    axiosInstance.get(`/api/offers/eligible/${customerId}`)
                ])
                .then(([allRes, availRes]) => {
                    setOffers(allRes.data);
                    setAvailableOffers(availRes.data.map(o => o.offerCode));
                })
                .catch(() => setMessage("Failed to load offers."));
            } else {
                // Fetch only available offers
                axiosInstance.get(`/api/offers/eligible/${customerId}`)
                    .then((res) => {
                        setOffers(res.data);
                    })
                    .catch(() => setMessage("Failed to load offers."));
            }
        }, [showAll, customerId]);

        const handleAvail = (offerCode) => {
            axiosInstance.post('/api/customer-offers/avail', {
                customerId,
                offerCode
            })
            .then(() => {
                setMessage(`Offer ${offerCode} availed successfully!`);
                setOffers((prev) => prev.filter((offer) => offer.offerCode !== offerCode));
            })
            .catch(() => {
                setMessage(`Failed to avail offer ${offerCode}.`);
            });
        };

        const isOfferAvailable = (code) => availableOffers.includes(code);

        return (
            <div className="offers-page">
                <h2>{showAll ? "All Offers in System" : "Available Offers"}</h2>

                <button onClick={() => setShowAll(!showAll)}>
                    {showAll ? "Show Available Only" : "Show All"}
                </button>

                {message && <p>{message}</p>}

                {offers.length === 0 ? (
                    <p>No offers to display.</p>
                ) : (
                    <ul>
                        {offers.map((offer) => (
                            <li key={offer.offerCode}>
                                <strong>{offer.offerName}</strong> - {offer.description}
                                <button 
                                    onClick={() => handleAvail(offer.offerCode)}
                                    disabled={showAll && !isOfferAvailable(offer.offerCode)}
                                    style={{ 
                                        marginLeft: "10px", 
                                        opacity: (showAll && !isOfferAvailable(offer.offerCode)) ? 0.5 : 1,
                                        cursor: (showAll && !isOfferAvailable(offer.offerCode)) ? 'not-allowed' : 'pointer'
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

    export default OffersAvailablePage;
