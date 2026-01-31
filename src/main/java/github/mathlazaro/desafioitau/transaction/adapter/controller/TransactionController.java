package github.mathlazaro.desafioitau.transaction.adapter.controller;

import github.mathlazaro.desafioitau.transaction.adapter.dto.CreateTransactionRequest;
import github.mathlazaro.desafioitau.transaction.adapter.presenter.TransactionPresenter;
import github.mathlazaro.desafioitau.transaction.application.TransactionService;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoints for managing transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction with the provided details.")
    public void createTransaction(
            @Parameter(description = "The transaction details", required = true)
            @Valid @RequestBody CreateTransactionRequest request
    ) {
        Transaction domain = TransactionPresenter.toDomain(request);
        transactionService.createTransaction(domain);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete all transactions", description = "Deletes all existing transactions from the system.")
    public void delelteAllTransactions() {
        transactionService.deleteAllTransactions();
    }

}
