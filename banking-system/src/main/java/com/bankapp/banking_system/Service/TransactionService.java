package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.Transactions;


public interface TransactionService {
	Transactions saveTransaction(Transactions transaction);
    Optional<Transactions> getTransactionById(Long id);
    List<Transactions> getTransactionsByAccount(Long accountId);
    void deleteTransaction(Long id);

}
