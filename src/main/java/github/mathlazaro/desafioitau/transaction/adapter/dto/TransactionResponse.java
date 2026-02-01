package github.mathlazaro.desafioitau.transaction.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TransactionResponse(
        @Schema(description = "Unique identifier of the transaction", example = "1")
        Long id,
        @Schema(description = "Transaction amount", example = "100.50")
        Double amount,
        @Schema(description = "Transaction date and time in ISO 8601 format", example = "2023-10-01T12:30:00Z")
        String dateTime
) {
}
