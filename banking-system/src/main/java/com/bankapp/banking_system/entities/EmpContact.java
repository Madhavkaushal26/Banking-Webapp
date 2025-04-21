package com.bankapp.banking_system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "emp_contacts")
@Data
public class EmpContact {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contactId")
	private long contactID;
	
	@ManyToOne
    @JoinColumn(name = "employee_id")
	@JsonBackReference
    private Employee employee;
	
	@Column(name = "email",length = 255)
	private String email;
	
	@Column(name = "phNo",length = 11)
	private String phNo;
	
	@Column(name = "address", columnDefinition = "TEXT")
    private String address;
}
