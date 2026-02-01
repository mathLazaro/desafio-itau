CREATE TABLE IF NOT EXISTS "transactions"
(
    "id"          BIGINT AUTO_INCREMENT PRIMARY KEY,
    "amount"      DECIMAL(19, 2) NOT NULL,
    "occurred_at" TIMESTAMP      NOT NULL
);
