package github.mathlazaro.desafioitau.transaction.infra.persistence.repository;

import github.mathlazaro.desafioitau.transaction.domain.model.Transaction;
import github.mathlazaro.desafioitau.transaction.domain.repository.TransactionRepository;
import github.mathlazaro.desafioitau.transaction.infra.persistence.entity.TransactionEntity;
import github.mathlazaro.desafioitau.transaction.infra.persistence.mapper.TransactionPersistenceMapper;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
public class TransactionJdbcRepository implements TransactionRepository {

    private final JdbcAggregateTemplate template;

    private final TransactionPersistenceMapper mapper;

    public TransactionJdbcRepository(JdbcAggregateTemplate template, TransactionPersistenceMapper mapper) {
        this.mapper = mapper;
        this.template = template;
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return Optional.ofNullable(template.findById(id, TransactionEntity.class))
                .map(mapper::toDomain);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return template.findAll(TransactionEntity.class)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Transaction> getAllTransactionsAtLast(int seconds) {
        Query query = query(where("dateTime").greaterThan(OffsetDateTime.now().minusSeconds(seconds)));
        return template.findAll(query, TransactionEntity.class)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Transaction saveTransaction(Transaction transactionToSave) {
        TransactionEntity entity = mapper.toEntity(transactionToSave);
        return Optional.of(template.insert(entity))
                .map(mapper::toDomain)
                .orElseThrow();
    }

    @Override
    public void deleteAllTransactions() {
        template.deleteAll(TransactionEntity.class);
    }
}
