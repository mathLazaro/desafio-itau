package github.mathlazaro.desafioitau.transaction.infra.persistence.registry;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TransactionRegistry {

    @Getter
    private static final List<Transaction> transactions = new ArrayList<>();

    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public static void clearTransactions() {
        transactions.clear();
    }

}
