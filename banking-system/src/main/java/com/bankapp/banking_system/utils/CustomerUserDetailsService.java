package com.bankapp.banking_system.utils;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustomerRepo;
import com.bankapp.banking_system.entities.Customer;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByCustomerId(customerId)
            .orElseThrow(() -> new UsernameNotFoundException("Customer not found with ID: " + customerId));

        return new org.springframework.security.core.userdetails.User(
            customer.getCustomerId(),
            customer.getPassword(),
            new ArrayList<>()
        );
    }
}