package github.mathlazaro.desafioitau.transaction.infra.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("transactions")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("amount")
    private Double amount;

    @Column("occurred_at")
    private Instant occurredAt;

    @Column("offset_occurred_at")
    private String offsetOccurredAt;

}
