package com.bankapp.banking_system.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustAccRepo;
import com.bankapp.banking_system.Repository.CustomerRepo;
import com.bankapp.banking_system.Service.CustAccService;
import com.bankapp.banking_system.entities.CustAccount;
import com.bankapp.banking_system.entities.Customer;
import com.bankapp.banking_system.utils.UnauthorizedAccessException;



@Service 
public class CustAccServImpl implements CustAccService {
	
	@Autowired
    private CustAccRepo custAccRepository;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CustAccount saveAccount(CustAccount account) {
		String custId = account.getCustomer().getCustomerId();
		
		Customer customer = customerRepo.findByCustomerId(custId)
		        .orElseThrow(() -> new IllegalArgumentException("Wrong CustomerId"));
		
		if ("N".equals(customer.getKycStatus())) {
	        throw new UnauthorizedAccessException("KYC Pending");
	    }
		account.setCustomer(customer);
		 return custAccRepository.save(account);
	}

	@Override
	public Optional<CustAccount> getAccountById(Long id) {
		
		return custAccRepository.findById(id);
	}

	@Override
	public List<CustAccount> getAccountsByCustomerId(String customerId) {
		
		return custAccRepository.findByCustomer_CustomerId(customerId);
	}

	@Override
	public void deleteAccount(Long id) {
		
		custAccRepository.deleteById(id);
	}

}
