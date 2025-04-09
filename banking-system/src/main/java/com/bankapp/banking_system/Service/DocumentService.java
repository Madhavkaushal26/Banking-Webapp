package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.CustDocuments;

public interface DocumentService {
	
	CustDocuments saveDocument(CustDocuments document);
    Optional<CustDocuments> getDocumentById(Long id);
    List<CustDocuments> getDocumentsByCustomerId(String customerId);
    void deleteDocument(Long id);
	
}
