package github.mathlazaro.desafioitau.transaction.domain.repository;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getAllTransactions();

    List<Transaction> getAllTransactionsAtLast(int seconds);

    void saveTransaction(Transaction transactionToSave);

    void deleteAllTransactions();

}
