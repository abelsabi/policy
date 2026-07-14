package com.assignment.policy.controller;

import com.assignment.policy.dto.request.ProposalRequest;
import com.assignment.policy.dto.response.ApiResponse;
import com.assignment.policy.dto.response.ProposalResponse;
import com.assignment.policy.service.ProposalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProposalResponse>> createProposal(
            @Valid @RequestBody ProposalRequest request) {

        ProposalResponse response =
                proposalService.createProposal(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Proposal created successfully.",
                        response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProposalResponse>> getProposal(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Proposal fetched successfully.",
                        proposalService.getProposalById(id)
                ));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<ApiResponse<ProposalResponse>> submitProposal(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Proposal submitted successfully.",
                        proposalService.submitProposal(id)
                ));
    }

}