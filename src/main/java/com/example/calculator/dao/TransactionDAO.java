package com.example.calculator.dao;

import com.example.calculator.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TransactionDAO {

    /**
     * This is kept package private to be used in test cases.
     * This field is holding some dummy transactions to demo this application.
     * In real world, this class will be reading the data from some database.
     */
    List<Transaction> allTransactions = new ArrayList<Transaction>() {{
        // Customer 1 transactions
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 40));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 13, 2), 51));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 5, 1, 45), 120));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 30));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 13, 2), 57));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 5, 1, 45), 160));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 17));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 13, 2), 76));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 5, 1, 45), 131));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 48));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 13, 2), 96));
        add(new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 5, 1, 45), 151));

        // Customer 2 transactions
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 11));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 13, 2), 54));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 5, 1, 45), 145));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 31));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 13, 2), 65));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 5, 1, 45), 170));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 19));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 13, 2), 96));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 5, 1, 45), 191));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 29));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 13, 2), 56));
        add(new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 5, 1, 45), 178));
    }};

    public Map<Integer, List<Transaction>> getAllTransactions() {
        if (allTransactions == null) {
            return Collections.emptyMap();
        } else {
            return allTransactions.stream().collect(Collectors.groupingBy(Transaction::getCustomerId));
        }
    }

    public List<Transaction> getTransactionsByCustomerId(int customerId) {
        if (allTransactions == null) {
            return Collections.emptyList();
        } else {
            return allTransactions.stream().filter(txn -> txn.getCustomerId() == customerId).collect(Collectors.toList());
        }
    }
}
