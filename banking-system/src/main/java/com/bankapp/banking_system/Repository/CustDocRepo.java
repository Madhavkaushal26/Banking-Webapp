package com.bankapp.banking_system.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapp.banking_system.entities.CustDocuments;
import com.bankapp.banking_system.entities.Customer;

public interface CustDocRepo extends JpaRepository<CustDocuments, Long> {
	
	List<CustDocuments> findByCustomer(Customer customer);

    Optional<CustDocuments> findByDocumentType(String documentType);
    
    List<CustDocuments> findByCustomer_CustomerId(String customerId);

}
