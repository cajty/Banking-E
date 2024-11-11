package org.ably.bankinge.controller;

import org.ably.bankinge.domain.dto.LoanDTO;
import org.ably.bankinge.domain.request.LoanRequest;
import org.ably.bankinge.service.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/loan")
@Tag(name = "Loan Controller", description = "Loan management APIs")
@AllArgsConstructor
public class LoanController {

    private final LoanService publicV1LoanService;

    @Operation(summary = "Create new loan")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanDTO save(@RequestBody @Valid final LoanRequest loanRequest) {
        return publicV1LoanService.save(loanRequest);
    }

    @Operation(summary = "Get all loans")
    @GetMapping("/")
    public List<LoanDTO> findAll() {
        return publicV1LoanService.findAll();
    }

    @Operation(summary = "Delete loan by ID")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final Long id) {
        publicV1LoanService.delete(id);
    }

    @Operation(summary = "Get loan by ID")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Optional<LoanDTO> findById(@PathVariable final Long id) {
        return publicV1LoanService.findById(id);
    }
}