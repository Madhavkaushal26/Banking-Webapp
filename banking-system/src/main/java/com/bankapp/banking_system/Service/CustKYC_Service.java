package com.bankapp.banking_system.Service;

import java.util.Optional;

import com.bankapp.banking_system.entities.CustKYC;

public interface CustKYC_Service {
	
	CustKYC saveKYC(CustKYC customerKYC);
    Optional<CustKYC> getKYCById(Long id);
    Optional<CustKYC> getKYCByCustomerAadhar(String aadharId);
    CustKYC updateKYC(String customerid, CustKYC updatedKYC);
    void deleteKYC(Long id);
    Optional<CustKYC> getKYCByCustomerId(String customerId);

	
}
