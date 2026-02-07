package github.mathlazaro.desafioitau.transaction.infra.persistence.mapper;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.infra.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionPersistenceMapper {

    @Mapping(target = "occurredAt", expression = "java(transaction.getDateTime().toInstant())")
    @Mapping(target = "offsetOccurredAt", expression = "java(transaction.getDateTime().getOffset().getId())")
    TransactionEntity toEntity(Transaction transaction);

    @Mapping(
            target = "dateTime",
            expression = "java(OffsetDateTime.ofInstant(transactionEntity.getOccurredAt(), java.time.ZoneOffset.of(transactionEntity.getOffsetOccurredAt())))"
    )
    Transaction toDomain(TransactionEntity transactionEntity);
}
