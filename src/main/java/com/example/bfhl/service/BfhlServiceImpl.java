package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${app.user.name:sujal_arora}")
    private String fullName;

    @Value("${app.user.dob:17091999}")
    private String dob;

    @Value("${app.user.email:sujal6067@gmail.com}")
    private String email;

    @Value("${app.user.roll-number:2110990000}")
    private String rollNumber;

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        if (request == null || request.data() == null) {
            return BfhlResponse.failure();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        StringBuilder originalAlphabetChars = new StringBuilder();

        for (String item : request.data()) {
            if (item == null) {
                continue;
            }

            // Check for integer (allowing negative integers as well)
            if (item.matches("^-?\\d+$")) {
                BigInteger num = new BigInteger(item);
                sum = sum.add(num);
                if (num.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } 
            // Check for pure alphabets
            else if (item.matches("^[a-zA-Z]+$")) {
                alphabets.add(item.toUpperCase());
                originalAlphabetChars.append(item);
            } 
            // Special characters
            else {
                specialCharacters.add(item);
            }
        }

        // Calculate concat_string
        // 1. Reverse the original alphabetical characters collected in original order
        String reversed = originalAlphabetChars.reverse().toString();

        // 2. Apply alternating caps starting with uppercase
        StringBuilder concatBuilder = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                concatBuilder.append(Character.toUpperCase(c));
            } else {
                concatBuilder.append(Character.toLowerCase(c));
            }
        }
        String concatString = concatBuilder.toString();

        // Construct User ID: {full_name_ddmmyyyy} in lowercase
        String formattedName = fullName.trim().replaceAll("\\s+", "_").toLowerCase();
        String userId = formattedName + "_" + dob;

        return new BfhlResponse(
            true,
            userId,
            email,
            rollNumber,
            oddNumbers,
            evenNumbers,
            alphabets,
            specialCharacters,
            sum.toString(),
            concatString
        );
    }
}
