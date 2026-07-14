package com.assignment.policy.repository;

import com.assignment.policy.entity.Proposal;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProposalRepository {

    private final ConcurrentHashMap<Long, Proposal> proposals = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public Proposal save(Proposal proposal) {

        if (proposal.getId() == null) {
            proposal.setId(idGenerator.getAndIncrement());
        }

        proposals.put(proposal.getId(), proposal);

        return proposal;
    }

    public Proposal findById(Long id) {
        return proposals.get(id);
    }

    public Collection<Proposal> findAll() {
        return proposals.values();
    }

}