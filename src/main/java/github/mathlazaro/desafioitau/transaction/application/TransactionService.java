package github.mathlazaro.desafioitau.transaction.application;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.domain.repository.TransactionRepository;
import org.springframework.stereotype.Component;

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

}
