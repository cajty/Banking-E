package org.ably.bankinge.service;

import org.ably.bankinge.domain.dto.LoanDTO;
import org.ably.bankinge.domain.entities.Loan;
import org.ably.bankinge.domain.request.LoanRequest;
import org.ably.bankinge.mapper.LoanMapper;
import org.ably.bankinge.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanDTO save(LoanRequest loanRequest) {
        Loan loan = loanMapper.toEntity(loanRequest);
        return loanMapper.toDTO(loanRepository.save(loan));
    }

    public List<LoanDTO> findAll() {
        return loanMapper.toDTOList(loanRepository.findAll());
    }

    public Optional<LoanDTO> findById(Long id) {
        return loanRepository.findById(id).map(loanMapper::toDTO);
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}
