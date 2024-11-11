package org.ably.bankinge.service;

import org.ably.bankinge.domain.dto.AccountDTO;
import org.ably.bankinge.domain.entities.Account;
import org.ably.bankinge.domain.request.AccountRequest;
import org.ably.bankinge.mapper.AccountMapper;
import org.ably.bankinge.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO save(AccountRequest accountRequest) {
        Account account = accountMapper.toEntity(accountRequest);
        return accountMapper.toDTO(accountRepository.save(account));
    }

    public List<AccountDTO> findAll(){
        return accountMapper.toDTOList(accountRepository.findAll());
    }

    public Optional<AccountDTO> findById(UUID id) {
        return accountRepository.findById(id).map(accountMapper::toDTO);
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
