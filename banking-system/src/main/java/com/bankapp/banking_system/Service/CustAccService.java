package com.bankapp.banking_system.Service;

import java.util.List;

import com.bankapp.banking_system.dto.AccCreationRequest;
import com.bankapp.banking_system.entities.CustAccount;
import com.bankapp.banking_system.entities.Customer;

public interface CustAccService {
	CustAccount saveAccount(AccCreationRequest account);
    List<CustAccount> getAccountsByCustomerId(String customerId);
    void deleteAccount(Long id);
	Customer getCustomerByAccountNumber(String accountNumber);	
}
