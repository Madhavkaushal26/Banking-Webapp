package com.bankapp.banking_system.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FinalUserDetails finalUserDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/employee/login")
    public ResponseEntity<?> employeeLogin(@RequestBody AuthRequest authRequest) {
        try {
            UserDetails userDetails = finalUserDetails.loadUserByUsername2(authRequest.getUsername(), "employee");

            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            Map<String, String> response = new HashMap<>();
            response.put("username", userDetails.getUsername());
            response.put("role", "employee");
            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/customer/login")
    public ResponseEntity<?> customerLogin(@RequestBody AuthRequest authRequest) {
        try {
            UserDetails userDetails = finalUserDetails.loadUserByUsername2(authRequest.getUsername(), "customer");

            if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

            Map<String, String> response = new HashMap<>();
            response.put("username", userDetails.getUsername());
            response.put("role", "customer");
            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
