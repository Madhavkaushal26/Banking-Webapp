package com.bankapp.banking_system.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.banking_system.Service.DocumentService;
import com.bankapp.banking_system.entities.CustDocuments;

@RestController
@RequestMapping("/api/documents")
public class CustDocsController {
	
	private final DocumentService documentService;

    public CustDocsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Upload a document
    @PostMapping
    public ResponseEntity<CustDocuments> uploadDocument(@RequestBody CustDocuments document) {
        CustDocuments savedDocument = documentService.saveDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    // Get document by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustDocuments> getDocumentById(@PathVariable Long id) {
        Optional<CustDocuments> document = documentService.getDocumentById(id);
        return document.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all documents of a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustDocuments>> getDocumentsByCustomer(@PathVariable String customerId) {
        List<CustDocuments> documents = documentService.getDocumentsByCustomerId(customerId);
        return ResponseEntity.ok(documents);
    }

    // Delete a document
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

}
