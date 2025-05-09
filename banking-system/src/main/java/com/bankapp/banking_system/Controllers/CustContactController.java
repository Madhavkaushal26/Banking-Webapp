package com.bankapp.banking_system.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.banking_system.Service.CustContactService;
import com.bankapp.banking_system.dto.ContactDTO;
import com.bankapp.banking_system.entities.CustContact;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/contacts")
public class CustContactController {
	
	private final CustContactService custContactService;

    public CustContactController(CustContactService custContactService) {
        this.custContactService = custContactService;
    }

    // Create a new contact
    @PostMapping("/register")
    public ResponseEntity<CustContact> createContact(@RequestBody ContactDTO contactDTO) {
        CustContact savedContact = custContactService.addContact(contactDTO);
        return ResponseEntity.ok(savedContact);
    }


    // Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustContact> getContactById(@PathVariable Long id) {
        Optional<CustContact> contact = custContactService.getContactById(id);
        return contact.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all contacts of a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustContact>> getContactsByCustomer(@PathVariable String customerId) {
        List<CustContact> contacts = custContactService.getContactsByCustomerId(customerId);
        return ResponseEntity.ok(contacts);
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        custContactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

}
