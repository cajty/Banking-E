package org.ably.bankinge.service;

import org.ably.bankinge.domain.dto.TransactionDTO;
import org.ably.bankinge.domain.entities.Transaction;
import org.ably.bankinge.domain.request.TransactionRequest;
import org.ably.bankinge.exception.TransactionNotFoundException;
import org.ably.bankinge.mapper.TransactionMapper;
import org.ably.bankinge.repository.AccountRepository;
import org.ably.bankinge.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final  AccountService accountService;
    private final TransactionMapper transactionMapper;

    public TransactionDTO save(TransactionRequest transactionRequest) {
        try {
            accountService.findById(transactionRequest.getAccountSenderId());
            accountService.findById(transactionRequest.getAccountReceiverId());

            Transaction transaction = transactionMapper.toEntity(transactionRequest);
            return transactionMapper.toDTO(transactionRepository.save(transaction));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransactionDTO> findAll(){
        return transactionMapper.toDTOList(transactionRepository.findAll());
    }

    public void delete(Long id){
        transactionRepository.deleteById(id);
    }

    public Optional<TransactionDTO> findById(Long id){
        return Optional.ofNullable(transactionRepository.findById(id)
                .map(transactionMapper::toDTO)
                .orElseThrow(TransactionNotFoundException::new));
    }

}
