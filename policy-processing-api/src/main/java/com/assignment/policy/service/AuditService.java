package com.assignment.policy.service;

import com.assignment.policy.entity.Audit;
import com.assignment.policy.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public void createAudit(Long proposalId, String action) {

        Audit audit = Audit.builder()
                .proposalId(proposalId)
                .action(action)
                .timestamp(LocalDateTime.now())
                .build();

        auditRepository.save(audit);

        log.info(
                "Audit created for proposal {} with action {}",
                proposalId,
                action
        );

    }

    public List<Audit> getAllAudits() {
        return auditRepository.findAll()
                .stream()
                .toList();
    }

}