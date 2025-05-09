package com.bankapp.banking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByContacts_PhNo(String PhoneNumber);

	Optional<Customer> findByCustomerId(String customerId);

	
}
