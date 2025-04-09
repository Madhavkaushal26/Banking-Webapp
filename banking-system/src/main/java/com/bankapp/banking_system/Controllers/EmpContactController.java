package com.bankapp.banking_system.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.banking_system.ServiceImpl.EmpContactService;
import com.bankapp.banking_system.entities.EmpContact;

@RestController
@RequestMapping("/api/emp/contact")
public class EmpContactController {

    @Autowired
    private EmpContactService empContactService;

    @PostMapping("/add")
    public ResponseEntity<EmpContact> addContact(@RequestBody EmpContact contact) {
        EmpContact saved = empContactService.saveContact(contact);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<EmpContact>> getContacts(@PathVariable String employeeId) {
        List<EmpContact> contacts = empContactService.getContactsByEmployeeId(employeeId);
        return ResponseEntity.ok(contacts);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        empContactService.deleteContact(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }
}