package github.mathlazaro.desafioitau.transaction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public class Transaction {

    private Long id;
    private Double amount;
    private OffsetDateTime dateTime;

    public void validate() {
        if (isNull(amount) || isNull(dateTime)) {
            throw new IllegalArgumentException("Transaction amount and dateTime must not be null");
        }

        if (dateTime.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Transaction dateTime must not be in the future");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount must not be negative");
        }
    }

}
