package com.enzow.PeopleManagement.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class for encrypting passwords using {@link BCryptPasswordEncoder}
 *
 * <p><strong>Note:</strong> This class is used as a helper tool to manually generate encrypted passwords when inserting users directly into the database.
 * It is not yet used in production or automated processes.</p>
 *
 * <p><strong>Warning:</strong> The password is hardcoded for testing purposes.
 * This approach is not recommended for real-world or production environments.</p>
 *
 * @deprecated This is a temporary class and will be modified in a future version, where dynamic user creation will be implemented
 */
public class EncryptPassword {

    /**
     * Starting point to create a hardcoded encrypted password to use as test in the app, which is shown in the console
     *
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        var password = "1234";
        System.out.println("Password to use: " + password);
        System.out.println("Password encrypted in the DB: " + encryptPassword(password));
    }

    /**
     * Encrypts a password using the BCrypt algorithm
     *
     * @param password Password to be encrypted
     * @return Encrypted password
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
