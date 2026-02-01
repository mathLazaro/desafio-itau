package github.mathlazaro.desafioitau.transaction.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record CreateTransactionRequest(
        @NotNull(message = "Amount must not be null")
        @PositiveOrZero(message = "Amount must be positive")
        @Schema(description = "Transaction amount. Must be positive", example = "100.50")
        Double amount,

        @NotNull(message = "DateTime must not be null")
        @Schema(description = "Transaction date and time in ISO 8601 format", example = "2023-10-01T12:30:00Z")
        OffsetDateTime dateTime
) {
}
