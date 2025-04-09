package com.bankapp.banking_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.bankapp.banking_system.Repository.EmployeeRepo;
import com.bankapp.banking_system.entities.Employee;

@ComponentScan(basePackages = "com.bankapp.banking_system")
@SpringBootApplication
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}
	@Bean
    CommandLineRunner createDefaultAdmin(EmployeeRepo employeeRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (employeeRepository.findByEmployeeId("admin").isEmpty()) {
                Employee admin = new Employee();
                admin.setEmployeeId("admin");
                admin.setPassword(passwordEncoder.encode("admin")); // hashed automatically
                employeeRepository.save(admin);
                System.out.println("Default admin created: username=admin, password=admin");
            } else {
                System.out.println("Admin already exists");
            }
        };
    }

}
