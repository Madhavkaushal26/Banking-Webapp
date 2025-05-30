package com.bankapp.banking_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.CustOfferAvl;
import com.bankapp.banking_system.entities.Customer;

@Repository
public interface CustOfferRepo extends JpaRepository<CustOfferAvl, Long> {
	
	List<CustOfferAvl> findByCustomer(Customer customer);

	List<CustOfferAvl> findByCustomer_CustomerId(String customerId);
}
