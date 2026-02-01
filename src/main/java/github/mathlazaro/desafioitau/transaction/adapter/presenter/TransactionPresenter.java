package github.mathlazaro.desafioitau.transaction.adapter.presenter;

import github.mathlazaro.desafioitau.transaction.adapter.dto.CreateTransactionRequest;
import github.mathlazaro.desafioitau.transaction.adapter.dto.TransactionResponse;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;

public class TransactionPresenter {

    public static Transaction toDomain(CreateTransactionRequest request) {
        return new Transaction(
                null,
                request.amount(),
                request.dateTime()
        );
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDateTime().toString()
        );
    }

}
