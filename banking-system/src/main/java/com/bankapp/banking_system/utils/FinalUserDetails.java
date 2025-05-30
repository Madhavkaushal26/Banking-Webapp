
package com.bankapp.banking_system.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FinalUserDetails implements UserDetailsService {

	 @Autowired(required = false)
	    private CustomUserDetailsService employeeUserDetailsService;

	    @Autowired(required = false)
	    private CustomerUserDetailsService customerUserDetailsService;
	    
	    public UserDetails loadUserByUsername2(String username, String role) throws UsernameNotFoundException {
	        if ("employee".equalsIgnoreCase(role)) {
	            return employeeUserDetailsService.loadUserByUsername(username);
	        } else if ("customer".equalsIgnoreCase(role)) {
	            return customerUserDetailsService.loadUserByUsername(username);
	        } else {
	            throw new UsernameNotFoundException("Role not specified or invalid.");
	        }
	    }


	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Logic to determine if the user is an employee or customer
	    	if (username.equalsIgnoreCase("admin") || username.startsWith("EMP-")) {  // Example check for employee ID prefix
	            return employeeUserDetailsService.loadUserByUsername(username);
	        } else if (username.startsWith("CUST-")) {  // Example check for customer ID prefix
	            return customerUserDetailsService.loadUserByUsername(username);
	        } else {
	            throw new UsernameNotFoundException("User not found: " + username);
	        }
	    }

}