package github.mathlazaro.desafioitau.transaction.infra.persistence.mapper;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.infra.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {
    TransactionEntity toEntity(Transaction transaction);

    Transaction toDomain(TransactionEntity transactionEntity);
}
