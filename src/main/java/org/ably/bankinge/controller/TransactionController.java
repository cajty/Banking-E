package org.ably.bankinge.controller;

import org.ably.bankinge.domain.dto.TransactionDTO;
import org.ably.bankinge.domain.request.TransactionRequest;
import org.ably.bankinge.service.TransactionService;
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
@RequestMapping("/api/transaction")
@Tag(name = "Transaction Controller", description = "Transaction management APIs")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService publicV1TransactionService;

    @Operation(summary = "Create new transaction")
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO save(@RequestBody @Valid final TransactionRequest transactionRequest) {
        return publicV1TransactionService.save(transactionRequest);
    }

    @Operation(summary = "Get all transactions")
    @GetMapping("/")
    public List<TransactionDTO> findAll() {
        return publicV1TransactionService.findAll();
    }

    @Operation(summary = "Delete transaction by ID")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable final Long id) {
        publicV1TransactionService.delete(id);
    }

    @Operation(summary = "Get transaction by ID")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Optional<TransactionDTO> findById(@PathVariable final Long id) {
        return publicV1TransactionService.findById(id);
    }
}