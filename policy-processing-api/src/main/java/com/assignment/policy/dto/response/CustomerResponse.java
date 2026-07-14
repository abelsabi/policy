package com.assignment.policy.dto.response;

import java.time.LocalDate;

public record CustomerResponse(

        Long id,

        String firstName,

        String lastName,

        LocalDate dateOfBirth,

        String panNumber,

        String email,

        String mobileNumber

) {
}