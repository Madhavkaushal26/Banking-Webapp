package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.dto.ContactDTO;
import com.bankapp.banking_system.entities.CustContact;

public interface CustContactService {
	
	CustContact saveContact(CustContact contact);
    Optional<CustContact> getContactById(Long id);
    List<CustContact> getContactsByCustomerId(String customerId);
    void deleteContact(Long id);
    public CustContact addContact(ContactDTO dto);
	
}
