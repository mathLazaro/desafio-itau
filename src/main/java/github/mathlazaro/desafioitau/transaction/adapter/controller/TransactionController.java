package github.mathlazaro.desafioitau.transaction.adapter.controller;

import github.mathlazaro.desafioitau.shared.adapter.util.LocationBuilder;
import github.mathlazaro.desafioitau.transaction.adapter.dto.CreateTransactionRequest;
import github.mathlazaro.desafioitau.transaction.adapter.dto.TransactionResponse;
import github.mathlazaro.desafioitau.transaction.adapter.presenter.TransactionPresenter;
import github.mathlazaro.desafioitau.transaction.application.TransactionService;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoints for managing transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieves a transaction by its unique ID.")
    public TransactionResponse getTransactionById(
            @Parameter(description = "The ID of the transaction to retrieve", required = true)
            @PathVariable Long id
    ) {
        Transaction transaction = transactionService.getTransactionById(id);
        return TransactionPresenter.toTransactionResponse(transaction);
    }


    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction with the provided details.")
    public ResponseEntity<Void> createTransaction(
            @Parameter(description = "The transaction details", required = true)
            @Valid @RequestBody CreateTransactionRequest request
    ) {
        Transaction domain = TransactionPresenter.toDomain(request);
        Long id = transactionService.createTransaction(domain).getId();
        return ResponseEntity
                .created(LocationBuilder.buildLocation(id))
                .build();
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete all transactions", description = "Deletes all existing transactions from the system.")
    public void delelteAllTransactions() {
        transactionService.deleteAllTransactions();
    }

}
