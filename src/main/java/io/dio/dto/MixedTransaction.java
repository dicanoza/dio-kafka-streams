package io.dio.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class MixedTransaction {
    UUID transactionId;
    BigDecimal value;
    UUID accountId;
    String initiatorIban;
    String recipientIban;

    public MixedTransaction() {
    }

    public MixedTransaction(UUID transactionId, BigDecimal value, UUID accountId, String initiatorIban,
                            String recipientIban) {
        this.transactionId = transactionId;
        this.value = value;
        this.accountId = accountId;
        this.initiatorIban = initiatorIban;
        this.recipientIban = recipientIban;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public MixedTransaction setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public MixedTransaction setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public MixedTransaction setAccountId(UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getInitiatorIban() {
        return initiatorIban;
    }

    public MixedTransaction setInitiatorIban(String initiatorIban) {
        this.initiatorIban = initiatorIban;
        return this;
    }

    public String getRecipientIban() {
        return recipientIban;
    }

    public MixedTransaction setRecipientIban(String recipientIban) {
        this.recipientIban = recipientIban;
        return this;
    }

    @Override
    public String toString() {
        return "MixedTransaction{" +
                "transactionId=" + transactionId +
                ", value=" + value +
                ", accountId=" + accountId +
                ", initiatorIban='" + initiatorIban + '\'' +
                ", recipientIban='" + recipientIban + '\'' +
                '}';
    }
}
