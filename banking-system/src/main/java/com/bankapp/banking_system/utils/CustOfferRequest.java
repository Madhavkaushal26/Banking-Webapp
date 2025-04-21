package com.bankapp.banking_system.utils;

import lombok.Data;

@Data
public class CustOfferRequest {
    private String customerId;
    private String offerCode;
}
