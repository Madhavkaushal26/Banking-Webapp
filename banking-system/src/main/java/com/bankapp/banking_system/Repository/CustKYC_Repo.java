package com.bankapp.banking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.CustKYC;

@Repository
public interface CustKYC_Repo extends JpaRepository<CustKYC, Long> {
	
	 Optional<CustKYC> findByAadharId(String aadharId);
	 Optional<CustKYC> findByCustomer_CustomerId(String customerId);

}
