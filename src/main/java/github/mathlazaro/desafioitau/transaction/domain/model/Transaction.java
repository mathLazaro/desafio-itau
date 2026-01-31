package github.mathlazaro.desafioitau.transaction.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

import static java.util.Objects.isNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Double value;
    private OffsetDateTime dateTime;

    public void validate() {
        if (isNull(value) || isNull(dateTime)) {
            throw new IllegalArgumentException("Transaction value and dateTime must not be null");
        }

        if (dateTime.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Transaction dateTime must not be in the future");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Transaction value must not be negative");
        }
    }

}
