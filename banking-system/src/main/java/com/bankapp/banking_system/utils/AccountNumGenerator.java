package com.bankapp.banking_system.utils;

import java.security.SecureRandom;

public class AccountNumGenerator {
	private static final SecureRandom random = new SecureRandom();

    public static String generateAccountNumber() {
        long min = 100_000_000_000_00L; // Smallest 14-digit number
        long max = 999_999_999_999_99L; // Largest 14-digit number
        return String.valueOf(min + random.nextLong(max - min));
    }

}
