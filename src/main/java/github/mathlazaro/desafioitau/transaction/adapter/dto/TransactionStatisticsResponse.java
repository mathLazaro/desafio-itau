package github.mathlazaro.desafioitau.transaction.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TransactionStatisticsResponse(
        @Schema(description = "Total number of transactions", example = "5")
        Long count,
        @Schema(description = "Sum of all transaction values", example = "500.75")
        Double sum,
        @Schema(description = "Average transaction amount", example = "100.15")
        Double avg,
        @Schema(description = "Minimum transaction amount", example = "50.00")
        Double min,
        @Schema(description = "Maximum transaction amount", example = "150.00")
        Double max
) {
}
