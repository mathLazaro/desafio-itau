package github.mathlazaro.desafioitau.transaction.adapter.controller;

import github.mathlazaro.desafioitau.transaction.adapter.dto.TransactionStatisticsResponse;
import github.mathlazaro.desafioitau.transaction.application.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
@Tag(name = "Transaction Statistics", description = "Endpoints for retrieving transaction statistics")
public class TransactionStatisticController {

    private final TransactionService transactionService;

    public TransactionStatisticController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get transaction statistics", description = "Retrieves statistics for all transactions.")
    public TransactionStatisticsResponse getStatistics() {
        return transactionService.getStatistics();
    }

}
