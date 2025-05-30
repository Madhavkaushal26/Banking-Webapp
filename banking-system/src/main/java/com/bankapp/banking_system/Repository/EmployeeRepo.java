package com.bankapp.banking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	Optional<Employee> findByEmployeeId(String employeeid);
	

}
