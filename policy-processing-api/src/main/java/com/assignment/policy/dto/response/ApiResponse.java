package com.assignment.policy.dto.response;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}