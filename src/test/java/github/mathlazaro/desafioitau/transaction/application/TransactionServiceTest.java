package github.mathlazaro.desafioitau.transaction.application;

import github.mathlazaro.desafioitau.transaction.adapter.dto.TransactionStatisticsResponse;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.domain.repository.TransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private Clock clock;

    @Mock
    private TransactionRepository repository;

    private static final Clock fixedClock = Clock.fixed(
            Instant.parse("1979-11-30T12:00:00Z"),
            ZoneOffset.UTC
    );


    @Test
    @DisplayName("Should validate transaction before saving")
    void validateCreateTransaction() {
        OffsetDateTime dateTime = OffsetDateTime.now(fixedClock).minusSeconds(50);

        Transaction transaction = new Transaction(null, 10.0, dateTime);
        Transaction transactionWithId = new Transaction(1L, 10.0, dateTime);

        when(clock.instant())
                .thenReturn(fixedClock.instant());

        when(repository.saveTransaction(transaction))
                .thenReturn(transactionWithId);

        Transaction created = service.createTransaction(transaction);

        assertNotNull(created);
        assertEquals(transactionWithId.getId(), created.getId());
        assertEquals(transaction.getAmount(), created.getAmount());
        assertEquals(transaction.getDateTime(), created.getDateTime());

    }

    @Test
    @DisplayName("Should delete all transactions")
    void validateDeleteAllTransactions() {
        service.deleteAllTransactions();

        verify(repository).deleteAllTransactions();
    }

    @Test
    @DisplayName("Should return empty statistics when no transactions in last 60 seconds")
    void validateStatisticsBlankList() {
        int seconds = 60;

        when(repository.getAllTransactionsAtLast(seconds))
                .thenReturn(List.of());

        TransactionStatisticsResponse response = service.getStatistics(seconds);

        assertEquals(0L, response.count());
        assertEquals(0.0, response.sum());
        assertEquals(0.0, response.avg());
        assertEquals(0.0, response.min());
        assertEquals(0.0, response.max());
    }

    @Test
    @DisplayName("Should return correct statistics for transactions in last 60 seconds")
    void validateStatistics() {
        int seconds = 60;
        OffsetDateTime now = OffsetDateTime.now();

        Transaction t1 = new Transaction(null, 10.0, now);
        Transaction t2 = new Transaction(null, 20.0, now);
        Transaction t3 = new Transaction(null, 30.0, now);

        when(repository.getAllTransactionsAtLast(seconds))
                .thenReturn(List.of(t1, t2, t3));

        TransactionStatisticsResponse response = service.getStatistics(seconds);

        assertEquals(3, response.count());
        assertEquals(60.0, response.sum());
        assertEquals(20.0, response.avg());
        assertEquals(10.0, response.min());
        assertEquals(30.0, response.max());
    }


}