package com.bankapp.banking_system.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.banking_system.ServiceImpl.CustOfferServImpl;
import com.bankapp.banking_system.entities.CustOfferAvl;
import com.bankapp.banking_system.entities.OfferandReward;
import com.bankapp.banking_system.utils.CustOfferRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/customer-offers")
public class CustOfferController {
	
	@Autowired
    private CustOfferServImpl custOfferService;

    @PostMapping("/avail")
    public ResponseEntity<CustOfferAvl> createCustomerOffer(@RequestBody CustOfferRequest customerOffer) {
        CustOfferAvl savedOffer = custOfferService.saveCustomerOffer(customerOffer);
        return ResponseEntity.ok(savedOffer);
    }
    @GetMapping("/availed/{customerId}")
    public ResponseEntity<List<OfferandReward>> getOffersByCustomerId(@PathVariable String customerId){
    	List<CustOfferAvl> offersEntity = custOfferService.getOffersByCustomerId(customerId);
    	
    	List<OfferandReward> offersAvailed = offersEntity.stream().map(custOffer -> custOffer.getOfferReward()).collect(Collectors.toList());
    	
    	return  ResponseEntity.ok(offersAvailed);
    	
    }

}
