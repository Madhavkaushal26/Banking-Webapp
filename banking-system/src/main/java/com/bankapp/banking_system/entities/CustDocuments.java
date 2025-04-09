package com.bankapp.banking_system.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Documents")
public class CustDocuments {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Link to Customer

    @Column(nullable = false)
    private String documentType; // Aadhar, PAN, Address Proof, etc.

    @Column(nullable = false)
    private String fileName; // Original file name

    @Column(nullable = false)
    private String fileUrl; // Azure Blob Storage URL

    @Column(nullable = false)
    private LocalDateTime uploadedAt; // Timestam
}
