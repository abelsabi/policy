package com.assignment.policy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String panNumber;

    private String email;

    private String mobileNumber;
}