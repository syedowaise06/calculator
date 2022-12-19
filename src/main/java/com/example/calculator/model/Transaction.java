package com.example.calculator.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final int customerId;
    private final int transactionId;
    private final LocalDateTime transactionDateTime;
    private final double transactionAmount;

    public Transaction(int customerId, int transactionId, LocalDateTime transactionDateTime, double transactionAmount) {
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.transactionDateTime = transactionDateTime;
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return customerId == that.customerId && transactionId == that.transactionId && Double.compare(that.transactionAmount, transactionAmount) == 0 && Objects.equals(transactionDateTime, that.transactionDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, transactionId, transactionDateTime, transactionAmount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "customerId=" + customerId +
                ", transactionId=" + transactionId +
                ", transactionDateTime=" + transactionDateTime +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}
