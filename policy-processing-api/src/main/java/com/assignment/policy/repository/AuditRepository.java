package com.assignment.policy.repository;

import com.assignment.policy.entity.Audit;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AuditRepository {

    private final ConcurrentHashMap<Long, Audit> audits = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public Audit save(Audit audit) {

        if (audit.getId() == null) {
            audit.setId(idGenerator.getAndIncrement());
        }

        audits.put(audit.getId(), audit);

        return audit;
    }

    public Collection<Audit> findAll() {
        return audits.values();
    }

}