package com.assignment.policy.controller;

import com.assignment.policy.dto.response.ApiResponse;
import com.assignment.policy.enums.PaymentFrequency;
import com.assignment.policy.service.ReferenceMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reference-master")
@RequiredArgsConstructor
public class ReferenceMasterController {

    private final ReferenceMasterService referenceMasterService;

    @GetMapping("/{category}")
    public ResponseEntity<ApiResponse<List<PaymentFrequency>>> getReferenceData(
            @PathVariable String category) {

        if (!category.equalsIgnoreCase("payment-frequency")) {

            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(
                            false,
                            "Invalid category.",
                            null));

        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Reference data fetched successfully.",
                        referenceMasterService.getPaymentFrequencies()
                ));

    }

}