package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.CustAccount;

public interface CustAccService {
	CustAccount saveAccount(CustAccount account);
    Optional<CustAccount> getAccountById(Long id);
    List<CustAccount> getAccountsByCustomerId(String customerId);
    void deleteAccount(Long id);	
}
