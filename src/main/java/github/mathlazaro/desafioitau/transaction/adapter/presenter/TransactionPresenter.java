package github.mathlazaro.desafioitau.transaction.adapter.presenter;

import github.mathlazaro.desafioitau.transaction.adapter.dto.CreateTransactionRequest;
import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;

public class TransactionPresenter {

    public static Transaction toDomain(CreateTransactionRequest request) {
        return new Transaction(
                request.value(),
                request.dateTime()
        );
    }

}
