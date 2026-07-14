package com.assignment.policy.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CustomerRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Date of birth is required")
        LocalDate dateOfBirth,

        String panNumber,

        @Email(message = "Invalid email format")
        String email,

        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Invalid mobile number"
        )
        String mobileNumber

) {
}