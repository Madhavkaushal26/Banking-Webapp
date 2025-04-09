package com.bankapp.banking_system.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.EmpContactRepo;
import com.bankapp.banking_system.entities.EmpContact;

@Service
public class EmpContactService {

    @Autowired
    private EmpContactRepo empContactRepo;

    public EmpContact saveContact(EmpContact contact) {
        return empContactRepo.save(contact);
    }

    public List<EmpContact> getContactsByEmployeeId(String employeeId) {
        return empContactRepo.findByEmployee_EmployeeId(employeeId);
    }

    public void deleteContact(Long id) {
        empContactRepo.deleteById(id);
    }
}