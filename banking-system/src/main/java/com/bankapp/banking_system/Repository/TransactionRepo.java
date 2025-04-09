package com.bankapp.banking_system.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.CustAccount;
import com.bankapp.banking_system.entities.Transactions;


@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Long> {

	List<Transactions> findByFromAccountOrToAccount(CustAccount sender, CustAccount receiver);

    List<Transactions> findByFromAccount(CustAccount sender);  // Fetch outgoing transactions
    
    List<Transactions> findByToAccount(CustAccount receiver);  // Fetch incoming transactions

    List<Transactions> findBytransactionDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate); // Filter transactions by date range
}
