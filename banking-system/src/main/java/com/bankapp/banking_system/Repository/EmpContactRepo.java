package com.bankapp.banking_system.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapp.banking_system.entities.EmpContact;

public interface EmpContactRepo extends JpaRepository<EmpContact, Long> {
    List<EmpContact> findByEmployee_EmployeeId(String employeeId);
}