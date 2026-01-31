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

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionRepository repository;


    @Test
    @DisplayName("Should validate transaction before saving")
    void validateCreateTransaction() {
        Transaction transaction = mock(Transaction.class);

        service.createTransaction(transaction);

        verify(transaction).validate();
        verify(repository).saveTransaction(transaction);
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
        when(repository.getAllTransactionsAtLast(60))
                .thenReturn(List.of());

        TransactionStatisticsResponse response = service.getStatistics();

        assertEquals(0L, response.count());
        assertEquals(0.0, response.sum());
        assertEquals(0.0, response.avg());
        assertEquals(0.0, response.min());
        assertEquals(0.0, response.max());
    }

    @Test
    @DisplayName("Should return correct statistics for transactions in last 60 seconds")
    void validateStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction t1 = new Transaction(10.0, now);
        Transaction t2 = new Transaction(20.0, now);
        Transaction t3 = new Transaction(30.0, now);

        when(repository.getAllTransactionsAtLast(60))
                .thenReturn(List.of(t1, t2, t3));

        TransactionStatisticsResponse response = service.getStatistics();

        assertEquals(3, response.count());
        assertEquals(60.0, response.sum());
        assertEquals(20.0, response.avg());
        assertEquals(10.0, response.min());
        assertEquals(30.0, response.max());
    }


}