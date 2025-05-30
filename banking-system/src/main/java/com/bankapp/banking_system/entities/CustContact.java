package com.bankapp.banking_system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CustContact")
@Data
public class CustContact {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")
	
	private long contactID;
	
	@ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Foreign Key
	@JsonBackReference
    private Customer customer;
	
	@Column(name = "email",length = 255)
	private String email;
	
	@Column(name = "phNo",length = 11)
	private String phNo;
	
	@Column(name = "address", columnDefinition = "TEXT")
    private String address;
	
	
}
