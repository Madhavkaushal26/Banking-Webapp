package com.bankapp.banking_system.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionRequest {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private String pin; 

    // Getters and Setters
}
