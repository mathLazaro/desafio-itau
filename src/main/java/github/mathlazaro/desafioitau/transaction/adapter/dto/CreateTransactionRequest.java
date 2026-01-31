package github.mathlazaro.desafioitau.transaction.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.OffsetDateTime;

public record CreateTransactionRequest(
        @NotNull
        @PositiveOrZero
        @Schema(description = "Transaction value. Must be positive", example = "100.50")
        Double value,

        @NotNull
        @Past
        @Schema(description = "Transaction date and time in ISO 8601 format. Must be in the past", example = "2023-10-01T12:30:00Z")
        OffsetDateTime dateTime
) {
}
