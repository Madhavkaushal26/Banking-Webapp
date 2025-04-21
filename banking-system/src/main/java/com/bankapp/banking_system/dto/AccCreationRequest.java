package com.bankapp.banking_system.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccCreationRequest {
	
	private String customerId;
    private String accountType;
    private BigDecimal balance;
    private int nomi_id;
    private String pin;
    private String pass;

}
