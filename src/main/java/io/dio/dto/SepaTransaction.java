package io.dio.dto;

import java.util.UUID;

public class SepaTransaction {
    UUID transactionId;
    UUID accountId;
    String initiatorIban;
    String recipientIban;

    public SepaTransaction(UUID transactionId, UUID accountId, String initiatorIban, String recipientIban) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.initiatorIban = initiatorIban;
        this.recipientIban = recipientIban;
    }

    public SepaTransaction() {
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public SepaTransaction setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public SepaTransaction setAccountId(UUID accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getInitiatorIban() {
        return initiatorIban;
    }

    public SepaTransaction setInitiatorIban(String initiatorIban) {
        this.initiatorIban = initiatorIban;
        return this;
    }

    public String getRecipientIban() {
        return recipientIban;
    }

    public SepaTransaction setRecipientIban(String recipientIban) {
        this.recipientIban = recipientIban;
        return this;
    }

    @Override
    public String toString() {
        return "SepaTransaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", initiatorIban='" + initiatorIban + '\'' +
                ", recipientIban='" + recipientIban + '\'' +
                '}';
    }
}
