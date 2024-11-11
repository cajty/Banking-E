package org.ably.bankinge.controller;

import org.ably.bankinge.domain.dto.AccountDTO;
import  org.ably.bankinge.domain.request.AccountRequest;
import org.ably.bankinge.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account Controller", description = "Account management APIs")
@AllArgsConstructor
public class AccountController {

    private final AccountService publicV1AccountService;

    @Operation(summary = "Create new account")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO save(@RequestBody @Valid final AccountRequest accountRequest) {
        return publicV1AccountService.save(accountRequest);
    }

    @Operation(summary = "Get all accounts")
    @GetMapping("/")
    public List<AccountDTO> findAll() {
        return publicV1AccountService.findAll();
    }

    @Operation(summary = "Delete account by ID")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        publicV1AccountService.delete(id);
    }

    @Operation(summary = "Get account by ID")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Optional<AccountDTO> findById(@PathVariable final UUID id) {
        return publicV1AccountService.findById(id);
    }
}