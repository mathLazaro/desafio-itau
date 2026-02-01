package github.mathlazaro.desafioitau.transaction.infra.persistence.mapper;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.infra.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {

    @Mapping(target = "occurredAt", expression = "java(transaction.getDateTime().toInstant())")
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(target = "dateTime", expression = "java(transactionEntity.getOccurredAt().atOffset(java.time.ZoneOffset.UTC))")
    Transaction toDomain(TransactionEntity transactionEntity);
}
