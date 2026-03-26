package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {

    public static String validateNotEmpty(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
        return input.trim();
    }

    public static String validateEmail(String email) throws InvalidInputException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty.");
        }
        if (!email.contains("@")) {
            throw new InvalidInputException("Invalid email format.");
        }
        return email.trim();
    }

    public static int validatePositiveInt(String input, String fieldName) throws InvalidInputException {
        try {
            int value = Integer.parseInt(input.trim());
            if (value <= 0) {
                throw new InvalidInputException(fieldName + " must be a positive number.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid number.");
        }
    }
}
