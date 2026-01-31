package github.mathlazaro.desafioitau.transaction.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    @DisplayName("Should not throw exception for valid transaction when value is positive and dateTime is in the past")
    void validateSuccess() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().minusSeconds(10));
        assertDoesNotThrow(transaction::validate);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when value is null")
    void validateNullValue() {
        Transaction transaction = new Transaction(null, OffsetDateTime.now().minusSeconds(10));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, transaction::validate);
        assertEquals("Transaction value and dateTime must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when dateTime is null")
    void validateNullDateTime() {
        Transaction transaction = new Transaction(100.0, null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, transaction::validate);
        assertEquals("Transaction value and dateTime must not be null", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when dateTime is in the future")
    void validateFutureDateTime() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().plusSeconds(10));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, transaction::validate);
        assertEquals("Transaction dateTime must not be in the future", exception.getMessage());
    }

}