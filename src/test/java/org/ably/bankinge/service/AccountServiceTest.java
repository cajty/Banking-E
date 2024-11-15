package org.ably.bankinge.service;

import org.ably.bankinge.domain.dto.AccountDTO;
import org.ably.bankinge.domain.entities.Account;
import org.ably.bankinge.domain.enums.AccountStatus;
import org.ably.bankinge.domain.request.AccountRequest;
import org.ably.bankinge.exception.account.AccountNotFoundException;
import org.ably.bankinge.exception.account.InsufficientBalanceException;
import org.ably.bankinge.mapper.AccountMapper;
import org.ably.bankinge.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private AccountRequest testRequest;
    private AccountDTO testDTO;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        testAccount = new Account();
        testAccount.setId(testId);
        testAccount.setBalance(1000.0);
        testAccount.setStatus(AccountStatus.ACTIVE);

        testRequest = new AccountRequest();
        testDTO = new AccountDTO();
    }

    @Test
    void save_ShouldCreateNewAccount() {
        // Arrange
        when(accountMapper.toEntity(any(AccountRequest.class))).thenReturn(testAccount);
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);
        when(accountMapper.toDTO(any(Account.class))).thenReturn(testDTO);

        // Act
        AccountDTO result = accountService.save(testRequest);

        // Assert
        assertNotNull(result);
        verify(accountRepository).save(any(Account.class));
        verify(accountMapper).toDTO(any(Account.class));
    }

    @Test
    void findAll_ShouldReturnAllAccounts() {
        // Arrange
        List<Account> accounts = Arrays.asList(testAccount);
        when(accountRepository.findAll()).thenReturn(accounts);

        // Act
        List<Account> result = accountService.findAll();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(accountRepository).findAll();
    }

    @Test
    void findById_WhenAccountExists_ShouldReturnAccount() {
        // Arrange
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(testAccount));

        // Act
        Account result = accountService.findById(testId);

        // Assert
        assertNotNull(result);
        assertEquals(testId, result.getId());
        verify(accountRepository).findById(testId);
    }

    @Test
    void findById_WhenAccountNotFound_ShouldThrowException() {
        // Arrange
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> accountService.findById(testId));
        verify(accountRepository).findById(testId);
    }

    @Test
    void findByUserId_ShouldReturnUserAccounts() {
        // Arrange
        Long userId = 1L;
        List<Account> accounts = Arrays.asList(testAccount);
        when(accountRepository.findByUserId(any(Long.class))).thenReturn(accounts);

        // Act
        List<Account> result = accountService.findByUserId(userId);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(accountRepository).findByUserId(userId);
    }

    @Test
    void delete_WhenBalanceIsZero_ShouldDeleteAccount() {
        // Arrange
        testAccount.setBalance(0.0);
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(testAccount));
        doNothing().when(accountRepository).deleteById(any(UUID.class));

        // Act
        accountService.delete(testId);

        // Assert
        verify(accountRepository).deleteById(testId);
    }

    @Test
    void delete_WhenBalanceIsNotZero_ShouldThrowException() {
        // Arrange
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(testAccount));

        // Act & Assert
        assertThrows(InsufficientBalanceException.class, () -> accountService.delete(testId));
        verify(accountRepository, never()).deleteById(any());
    }

    @Test
    void transferMoney_WhenSufficientBalance_ShouldTransferSuccessfully() {
        // Arrange
        UUID receiverId = UUID.randomUUID();
        Account receiverAccount = new Account();
        receiverAccount.setId(receiverId);
        receiverAccount.setBalance(500.0);

        when(accountRepository.findById(eq(testId))).thenReturn(Optional.of(testAccount));
        when(accountRepository.findById(eq(receiverId))).thenReturn(Optional.of(receiverAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount, receiverAccount);

        // Act
        accountService.transferMoney(testId, receiverId, 500.0);

        // Assert
        assertEquals(500.0, testAccount.getBalance());
        assertEquals(1000.0, receiverAccount.getBalance());
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    void transferMoney_WhenInsufficientBalance_ShouldThrowException() {
        // Arrange
        UUID receiverId = UUID.randomUUID();
        Account receiverAccount = new Account();
        receiverAccount.setId(receiverId);
        receiverAccount.setBalance(500.0);

        when(accountRepository.findById(eq(testId))).thenReturn(Optional.of(testAccount));
        when(accountRepository.findById(eq(receiverId))).thenReturn(Optional.of(receiverAccount));

        // Act & Assert
        assertThrows(InsufficientBalanceException.class,
                () -> accountService.transferMoney(testId, receiverId, 1500.0));
        verify(accountRepository, never()).save(any(Account.class));
    }
}