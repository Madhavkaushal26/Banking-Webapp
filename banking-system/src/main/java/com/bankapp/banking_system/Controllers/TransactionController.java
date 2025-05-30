package com.bankapp.banking_system.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.banking_system.ServiceImpl.TransactionServImpl;
import com.bankapp.banking_system.dto.TransactionRequest;
import com.bankapp.banking_system.entities.Transactions;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
    private TransactionServImpl transactionService;

    // Create a new transaction
    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionRequest transaction) {
        Transactions savedTransaction = transactionService.makeTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable Long id) {
        Optional<Transactions> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all transactions for a specific account (debit or credit)
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transactions>> getTransactionsByAccount(@PathVariable Long accountId) {
        List<Transactions> transactions = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
