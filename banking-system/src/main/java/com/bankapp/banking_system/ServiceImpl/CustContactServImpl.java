package com.bankapp.banking_system.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustContactRepo;
import com.bankapp.banking_system.Repository.CustomerRepo;
import com.bankapp.banking_system.Service.CustContactService;
import com.bankapp.banking_system.dto.ContactDTO;
import com.bankapp.banking_system.entities.CustContact;
import com.bankapp.banking_system.entities.Customer;


@Service 
public class CustContactServImpl implements CustContactService {
	
	@Autowired
    private CustContactRepo custContactRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CustContact saveContact(CustContact contact) {
		 return custContactRepo.save(contact);
	}
	

	public CustContact addContact(ContactDTO dto) {
	    Customer customer = customerRepo.findByCustomerId(dto.getCustomerId())
	        .orElseThrow(() -> new RuntimeException("Customer not found"));
	
	    CustContact contact = new CustContact();
	    contact.setCustomer(customer);
	    contact.setEmail(dto.getEmail());
	    contact.setPhNo(dto.getPhNo());
	    contact.setAddress(dto.getAddress());
	
	    return custContactRepo.save(contact);
	}

	@Override
	public Optional<CustContact> getContactById(Long id) {

		
		return custContactRepo.findById(id);
	}

	@Override
	public List<CustContact> getContactsByCustomerId(String customerId) {
		
		return custContactRepo.findByCustomer_CustomerId(customerId);
	}

	@Override
	public void deleteContact(Long id) {
		
		custContactRepo.deleteById(id);
	}

}
