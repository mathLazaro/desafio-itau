package github.mathlazaro.desafioitau.transaction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
public class Transaction {

    private Long id;
    private Double amount;
    private OffsetDateTime dateTime;

    public void validate(Clock clock) {
        Instant now = clock.instant();

        if (isNull(amount) || isNull(dateTime)) {
            throw new IllegalStateException("Transaction amount and dateTime must not be null");
        }

        if (dateTime.toInstant().isAfter(now)) {
            throw new IllegalStateException("Transaction dateTime must not be in the future");
        }

        if (Duration.between(dateTime.toInstant(), now).getSeconds() > 60) {
            throw new IllegalStateException("Transaction dateTime must be within the last 60 seconds");
        }

        if (amount < 0) {
            throw new IllegalStateException("Transaction amount must not be negative");
        }
    }

}
