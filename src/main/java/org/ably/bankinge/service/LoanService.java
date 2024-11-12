package org.ably.bankinge.service;


import org.ably.bankinge.domain.entities.Loan;
import org.ably.bankinge.domain.request.LoanRequest;
import org.ably.bankinge.mapper.LoanMapper;
import org.ably.bankinge.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public Loan save(LoanRequest loanRequest) {
        Loan loan = loanMapper.toEntity(loanRequest);
        return loanRepository.save(loan);
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Loan not found with id " + id)
        );
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}
