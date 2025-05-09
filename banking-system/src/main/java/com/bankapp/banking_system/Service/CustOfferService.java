package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.CustOfferAvl;
import com.bankapp.banking_system.utils.CustOfferRequest;

public interface CustOfferService {
	
	CustOfferAvl saveCustomerOffer(CustOfferRequest customerOffer);
    Optional<CustOfferAvl> getCustomerOfferById(Long id);
    List<CustOfferAvl> getOffersByCustomerId(String customerId);
    void deleteCustomerOffer(Long id);
	
}
