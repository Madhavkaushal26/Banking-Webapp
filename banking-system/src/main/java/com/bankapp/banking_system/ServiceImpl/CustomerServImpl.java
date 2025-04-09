package com.bankapp.banking_system.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustomerRepo;
import com.bankapp.banking_system.Service.CustomerService;
import com.bankapp.banking_system.entities.Customer;

@Service
public class CustomerServImpl implements CustomerService {
	

    private final CustomerRepo customerRepo;
    private final Argon2PasswordEncoder passwordEncoder;
    
    @Autowired
    public CustomerServImpl(CustomerRepo CustomerRepo) {
        this.customerRepo = CustomerRepo;
        this.passwordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

	@Override
	public Customer saveCustomer(Customer customer) {
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		return customerRepo.findById(id);
	}

	@Override
	public Optional<Customer> getCustomerByPhNo(String phno) {
		return customerRepo.findByContacts_PhNo(phno);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepo.deleteById(id);

	}
	
	@Override
	public Customer updateCustomer(Long id, Customer customerDetails) {
	    Optional<Customer> existingCustomer = customerRepo.findById(id);
	    if (existingCustomer.isPresent()) {
	        Customer customer = existingCustomer.get();
	        customer.setFullName(customerDetails.getFullName());
	        customer.setDob(customerDetails.getDob());
	        customer.setGender(customerDetails.getGender());
	        customer.setNationality(customerDetails.getNationality());

	        return customerRepo.save(customer); // Save updated customer
	    } else {
	        throw new RuntimeException("Customer not found with ID: " + id);
	    }
	}


}
