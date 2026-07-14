package com.assignment.policy.controller;

import com.assignment.policy.dto.response.ApiResponse;
import com.assignment.policy.entity.Audit;
import com.assignment.policy.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audits")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Audit>>> getAudits() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Audit records fetched successfully.",
                        auditService.getAllAudits()
                ));

    }

}