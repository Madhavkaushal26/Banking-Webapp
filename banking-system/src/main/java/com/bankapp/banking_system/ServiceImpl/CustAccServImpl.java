package com.bankapp.banking_system.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.CustAccRepo;
import com.bankapp.banking_system.Repository.CustOfferRepo;
import com.bankapp.banking_system.Repository.CustomerRepo;
import com.bankapp.banking_system.Service.CustAccService;
import com.bankapp.banking_system.dto.AccCreationRequest;
import com.bankapp.banking_system.entities.CustAccount;
import com.bankapp.banking_system.entities.CustOfferAvl;
import com.bankapp.banking_system.entities.Customer;
import com.bankapp.banking_system.utils.AccountNumGenerator;
import com.bankapp.banking_system.utils.UnauthorizedAccessException;



@Service 
public class CustAccServImpl implements CustAccService {
	
	@Autowired
    private CustAccRepo custAccRepository;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired 
	private CustOfferRepo custOfferRepo;

	public CustAccount saveAccount(AccCreationRequest request) {
	    String custId = request.getCustomerId();

	    Customer customer = customerRepo.findByCustomerId(custId)
	            .orElseThrow(() -> new IllegalArgumentException("Wrong CustomerId"));

	    // Password verification
	    if (!customer.verifyPassword(request.getPass())) {
	        throw new SecurityException("Incorrect password.");
	    }

	    // KYC status check
	    if ("N".equals(customer.getKycStatus())) {
	        throw new UnauthorizedAccessException("KYC Pending");
	    }

	    // Create CustAccount entity
	    CustAccount account = new CustAccount();
	    account.setAccountType(request.getAccountType());
	    account.setBalance(request.getBalance());
	    account.setNomi_id(request.getNomi_id());
	    account.setPin(request.getPin()); // Assuming pin is encrypted/stored properly
	    account.setCustomer(customer);
	    
	    account.setAccountNumber(AccountNumGenerator.generateAccountNumber());
	    // Fetch the offers availed by the customer
	    List<CustOfferAvl> availedOffers = custOfferRepo.findByCustomer(customer);
	    boolean isWomenFin24Avl = false;
	    boolean isYouthBoostAvl = false;

	    for (CustOfferAvl offer : availedOffers) {
	        String offerName = offer.getOfferReward().getOfferCode();
	        if ("WOMEN_FIN24".equalsIgnoreCase(offerName)) {
	            isWomenFin24Avl = true;
	        } else if ("YouthBoost".equalsIgnoreCase(offerName)) {
	            isYouthBoostAvl = true;
	        }
	    }

	    // Set interest based on account type and offers
	    if ("Savings".equalsIgnoreCase(account.getAccountType())) {
	        if (isWomenFin24Avl) {
	            account.setInterest(8);
	        } else if (isYouthBoostAvl) {
	            account.setInterest(7);
	        } else {
	            account.setInterest(5);
	        }
	    } else if ("Current".equalsIgnoreCase(account.getAccountType())) {
	        if (isWomenFin24Avl) {
	            account.setInterest(2);
	        } else if (isYouthBoostAvl) {
	            account.setInterest(3);
	        } else {
	            account.setInterest(1);
	        }
	    }

	    return custAccRepository.save(account);
	}


	@Override
	public List<CustAccount> getAccountsByCustomerId(String customerId) {
		
		return custAccRepository.findByCustomer_CustomerId(customerId);
	}

	@Override
	public void deleteAccount(Long id) {
		
		custAccRepository.deleteById(id);
	}
	
	@Override
    public Customer getCustomerByAccountNumber(String accountNumber) {
        // Fetch the account using the account number
        CustAccount account = custAccRepository.findByAccountNumber(accountNumber).get();

        // If account exists, return the customer linked with the account
        if (account != null) {
            return account.getCustomer();
        } else {
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
    }

}
