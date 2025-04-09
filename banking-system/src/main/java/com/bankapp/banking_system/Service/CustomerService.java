package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.Customer;

public interface CustomerService {
	Customer saveCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    Optional<Customer> getCustomerByPhNo(String phno);
    List<Customer> getAllCustomers();
    void deleteCustomer(Long id);
    
    Customer updateCustomer(Long id, Customer customerDetails);


}
