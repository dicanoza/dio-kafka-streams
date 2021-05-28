package io.dio.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {
    UUID transactionId;
    BigDecimal value;
    UUID accountId;

    public Transaction() {
    }

    public Transaction(UUID transactionId, BigDecimal valueOf, UUID accountId) {
        this.transactionId = transactionId;
        value = valueOf;
        this.accountId = accountId;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public Transaction setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Transaction setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public Transaction setAccountId(UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", value=" + value +
                ", accountId=" + accountId +
                '}';
    }
}
