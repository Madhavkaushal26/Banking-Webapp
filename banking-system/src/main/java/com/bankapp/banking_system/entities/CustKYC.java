package com.bankapp.banking_system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CustKYC")
@Data
public class CustKYC {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kyc_id")
    private Long kycId; // Primary Key
	
	@OneToOne
	@JoinColumn(name = "customer_id",nullable = false, unique = true)
	@JsonBackReference
	private Customer customer;
	
	@Column(name = "aadhar_id", nullable = false, unique = true, length = 12)
    private String aadharId;
	
	@Column(name = "pan_id", nullable = false, unique = true, length = 10)
    private String panId;
	
	@Column(name = "address_proof")
    private String addressProof;
	
	@Column(name = "signature_specimen")
    private String signatureSpecimen;
	
	@PrePersist
    @PreUpdate
    public void updateCustomerKycStatus() {
        if (this.customer != null) {
            this.customer.updateKycStatus();
        }
    }

}
