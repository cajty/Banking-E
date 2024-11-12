package org.ably.bankinge.controller;

import org.ably.bankinge.domain.dto.AccountDTO;
import  org.ably.bankinge.domain.request.AccountRequest;
import org.ably.bankinge.mapper.AccountMapper;
import org.ably.bankinge.mapper.TransactionMapper;
import org.ably.bankinge.mapper.UserMapper;
import org.ably.bankinge.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account Controller", description = "Account management APIs")
@AllArgsConstructor
public class AccountController {

    private final AccountService AccountService;
    private final AccountMapper accountMapper;

    @Operation(summary = "Create new account")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO save(@RequestBody @Valid final AccountRequest accountRequest) {
        return AccountService.save(accountRequest);
    }

    @Operation(summary = "Get all accounts of a user")
    @GetMapping("/user/{userId}")
    public List<AccountDTO> findByUserId(@PathVariable final Long userId) {
        return accountMapper.toDTOList(AccountService.findByUserId(userId));
    }




    @Operation(summary = "Get all accounts")
    @GetMapping("/")
    public List<AccountDTO> findAll() {
        return accountMapper.toDTOList(AccountService.findAll());
    }

    @Operation(summary = "Delete account by ID")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        AccountService.delete(id);
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public AccountDTO findById(@PathVariable final UUID id) {
        return accountMapper.toDTO(AccountService.findById(id));
    }
}