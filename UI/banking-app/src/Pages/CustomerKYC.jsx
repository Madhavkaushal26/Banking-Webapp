import React, { useState } from "react";
import axiosInstance from "../Services/axiosInstance";

const CustomerKyc = () => {
    const [customerId, setCustomerId] = useState("");
    const [aadharId, setAadharId] = useState("");
    const [panId, setPanId] = useState("");
    const [addressProof, setAddressProof] = useState("");
    const [signatureSpecimen, setSignatureSpecimen] = useState("");
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async () => {
        if (!customerId || !aadharId || !panId || !addressProof || !signatureSpecimen) {
            setError("All fields are required.");
            setMessage("");
            return;
        }

        const requestData = {
            customer: {
                customerId: customerId
            },
            aadharId,
            panId,
            addressProof,
            signatureSpecimen
        };

        try {
            await axiosInstance.post("/api/kyc/register", requestData);
            setMessage("KYC submitted successfully.");
            setError("");
        } catch (err) {
            setError("KYC submission failed.");
            setMessage("");
        }
    };

    return (
        <div className="kyc-form p-4">
            <h2>Customer KYC Form</h2>

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
                <label>Aadhar ID:</label>
                <input
                    type="text"
                    value={aadharId}
                    onChange={(e) => setAadharId(e.target.value)}
                />
            </div>

            <div>
                <label>PAN ID:</label>
                <input
                    type="text"
                    value={panId}
                    onChange={(e) => setPanId(e.target.value)}
                />
            </div>

            <div>
                <label>Address Proof:</label>
                <input
                    type="text"
                    value={addressProof}
                    onChange={(e) => setAddressProof(e.target.value)}
                />
            </div>

            <div>
                <label>Signature Specimen (URL):</label>
                <input
                    type="text"
                    value={signatureSpecimen}
                    onChange={(e) => setSignatureSpecimen(e.target.value)}
                />
            </div>

            <button onClick={handleSubmit}>Submit KYC</button>
        </div>
    );
};

export default CustomerKyc;
