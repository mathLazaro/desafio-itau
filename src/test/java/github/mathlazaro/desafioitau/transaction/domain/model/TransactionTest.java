package github.mathlazaro.desafioitau.transaction.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private static final Clock fixedClock = Clock.fixed(
            Instant.parse("1979-11-30T12:00:00Z"),
            ZoneOffset.UTC
    );

    @Test
    @DisplayName("Should not throw exception for valid transaction when amount is positive and dateTime is in the past")
    void validateSuccess() {
        Transaction transaction = new Transaction(null, 100.0, OffsetDateTime.now(fixedClock).minusSeconds(10));
        assertDoesNotThrow(() -> transaction.validate(fixedClock.instant()));
    }

    @Test
    @DisplayName("Should throw IllegalStateException when amount is null")
    void validateNullValue() {
        Transaction transaction = new Transaction(null, null, OffsetDateTime.now(fixedClock).minusSeconds(10));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> transaction.validate(fixedClock.instant()));
        assertEquals("Transaction amount and dateTime must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalStateException when amount is negative")
    void validateNegativeValue() {
        Transaction transaction = new Transaction(null, -10.0, OffsetDateTime.now(fixedClock).minusSeconds(10));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> transaction.validate(fixedClock.instant()));
        assertEquals("Transaction amount must not be negative", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalStateException when dateTime is null")
    void validateNullDateTime() {
        Transaction transaction = new Transaction(null, 100.0, null);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> transaction.validate(fixedClock.instant()));
        assertEquals("Transaction amount and dateTime must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalStateException when dateTime is in the future")
    void validateFutureDateTime() {
        Transaction transaction = new Transaction(null, 100.0, OffsetDateTime.now(fixedClock).plusSeconds(10));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> transaction.validate(fixedClock.instant()));
        assertEquals("Transaction dateTime must not be in the future", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalStateException when transaction dateTime is older than 60 seconds")
    void validateOldDateTime() {
        Transaction transaction = new Transaction(null, 100.0, OffsetDateTime.now(fixedClock).minusSeconds(70));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> transaction.validate(fixedClock.instant()));
        assertEquals("Transaction dateTime must be within the last 60 seconds", exception.getMessage());
    }

}