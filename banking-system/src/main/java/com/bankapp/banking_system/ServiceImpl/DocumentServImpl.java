package com.bankapp.banking_system.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustDocRepo;
import com.bankapp.banking_system.Service.DocumentService;
import com.bankapp.banking_system.entities.CustDocuments;


@Service 
public class DocumentServImpl implements DocumentService {
	
	@Autowired
    private CustDocRepo custDocRepo;

	@Override
	public CustDocuments saveDocument(CustDocuments document) {
		
		return custDocRepo.save(document);
	}

	@Override
	public Optional<CustDocuments> getDocumentById(Long id) {
	
		return custDocRepo.findById(id);
	}

	@Override
	public List<CustDocuments> getDocumentsByCustomerId(String customerId) {
		
		return custDocRepo.findByCustomer_CustomerId(customerId);
	}

	@Override
	public void deleteDocument(Long id) {
		
		custDocRepo.deleteById(id);
	}

}
