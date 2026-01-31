package github.mathlazaro.desafioitau.transaction.application;

import github.mathlazaro.desafioitau.transaction.adapter.dto.TransactionStatisticsResponse;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.domain.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.repository = transactionRepository;
    }

    public void createTransaction(Transaction transaction) {
        transaction.validate();
        repository.saveTransaction(transaction);
    }

    public void deleteAllTransactions() {
        repository.deleteAllTransactions();
    }

    public TransactionStatisticsResponse getStatistics() {
        List<Transaction> transactions = repository.getAllTransactionsAtLast(60);
        long size = transactions.size();

        if (size == 0) {
            return new TransactionStatisticsResponse(0L, 0.0, 0.0, 0.0, 0.0);
        }

        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (var transaction : transactions) {
            double value = transaction.getValue();
            sum += value;
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }

        return new TransactionStatisticsResponse(size, sum, sum / size, min, max);
    }


}
