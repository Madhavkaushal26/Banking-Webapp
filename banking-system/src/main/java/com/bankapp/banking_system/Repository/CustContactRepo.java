package com.bankapp.banking_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.CustContact;
import com.bankapp.banking_system.entities.Customer;


@Repository
public interface CustContactRepo extends JpaRepository<CustContact, Long> {
	
	Optional<CustContact> findByPhNo(String phoneNumber);

    List<CustContact> findByCustomer(Customer customer);  // Get all contacts of a customer
    
    List<CustContact> findByCustomer_CustomerId(String customerId);

}
