package com.bankapp.banking_system.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Transactions")
@Data
public class Transactions {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trans_id")
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "Sender", nullable = false) // Sender's account 
    private CustAccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "Receiver", nullable = false) // Receiver's account 
    private CustAccount toAccount;
    

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime transactionDateTime;

    @PrePersist
    protected void onCreate() {
        transactionDateTime = LocalDateTime.now();
    }

}
