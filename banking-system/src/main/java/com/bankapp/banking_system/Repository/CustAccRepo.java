package com.bankapp.banking_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.CustAccount;

@Repository
public interface CustAccRepo extends JpaRepository<CustAccount,Long> {
	
	Optional<CustAccount> findByAccountNumber(String accountNumber);
	
	List<CustAccount> findByCustomer_CustomerId(String customerId);


}
