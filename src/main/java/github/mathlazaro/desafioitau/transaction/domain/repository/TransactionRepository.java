package github.mathlazaro.desafioitau.transaction.domain.repository;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Optional<Transaction> getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsAtLast(int seconds);

    Transaction saveTransaction(Transaction transactionToSave);

    void deleteAllTransactions();

}
