package com.bankapp.banking_system.entities;

import java.util.List;
import java.time.LocalDate;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import com.bankapp.banking_system.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "Customers")
@Data
public class Customer {
	@Id
    @Column(name = "customerId", nullable = false, unique = true)
    private String customerId;
	
	@Column(name = "Fname")
    private String fullName; 
	
	@Column(name = "Dob")
    private String dob;
	@Column(name = "gender")
    private String gender;
	@Column(name = "Nationality")
    private String nationality;
	

	@Column(name = "join_date", nullable = false)
	private LocalDate joinDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<CustContact> contacts;
   
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private CustKYC kyc;
    
    @Column(name = "kyc_status", length = 1)
    private String kycStatus = "N";  // Default is 'N' (No KYC record)

    // Method to update KYC status
    public void updateKycStatus() {
        this.kycStatus = (this.kyc != null) ? "Y" : "N";
    }
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CustAccount> accounts;
    
    @Column(nullable = false)
    private String password; // Encrypted password

    private static final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    
    public void setPassword(String password) {
        if (!password.startsWith("$argon2id$")) { // Prevent double encryption
            this.password = encoder.encode(password);
        }
    }

    // Method to verify Password (For Authentication)
    public boolean verifyPassword(String rawPassword) {
        return encoder.matches(rawPassword, this.password);
    }
    
    @PrePersist
    public void setCustomerId() {
        if (this.customerId == null) {
            this.customerId = IdGenerator.generateCustomerId();
        }
    }

}
