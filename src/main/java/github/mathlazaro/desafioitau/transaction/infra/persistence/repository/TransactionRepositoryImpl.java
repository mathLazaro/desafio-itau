package github.mathlazaro.desafioitau.transaction.infra.persistence.repository;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.domain.repository.TransactionRepository;
import github.mathlazaro.desafioitau.transaction.infra.persistence.registry.TransactionRegistry;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

//@Repository
@Deprecated
public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return List.copyOf(TransactionRegistry.getTransactions());
    }

    @Override
    public List<Transaction> getAllTransactionsAtLast(int seconds) {
        return TransactionRegistry.getTransactions()
                .stream()
                .filter(transaction -> transaction.getDateTime().isAfter(OffsetDateTime.now().minusSeconds(seconds)))
                .toList();
    }

    @Override
    public Transaction saveTransaction(Transaction transactionToSave) {
        TransactionRegistry.addTransaction(transactionToSave);
        return transactionToSave;
    }

    @Override
    public void deleteAllTransactions() {
        TransactionRegistry.clearTransactions();
    }

}
