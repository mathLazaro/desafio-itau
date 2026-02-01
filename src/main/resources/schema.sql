CREATE TABLE IF NOT EXISTS "transactions"
(
    "id"        BIGINT AUTO_INCREMENT PRIMARY KEY,
    "amount"    DECIMAL(19, 2) NOT NULL,
    "date_time" TIMESTAMP      NOT NULL
);
