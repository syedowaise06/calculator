package com.example.calculator.dao;

import com.example.calculator.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.*;

@SpringJUnitConfig
public class TransactionDAOTest {

    @InjectMocks
    private TransactionDAO transactionDAO;

    @Test
    public void testGetAllTransactionsReturnsAllCustomersTransactions() {
        Transaction t1 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 40);
        Transaction t2 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 30);
        Transaction t3 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 17);
        Transaction t4 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 48);

        // Customer 2 transactions
        Transaction t5 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 11);
        Transaction t6 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 31);
        Transaction t7 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 19);
        Transaction t8 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 29);

        transactionDAO.allTransactions = Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8);

        Map<Integer, List<Transaction>> transactions = transactionDAO.getAllTransactions();
        Map<Integer, List<Transaction>> expected = new HashMap<>();
        expected.put(1, Arrays.asList(t1, t2, t3, t4));
        expected.put(2, Arrays.asList(t5, t6, t7, t8));

        Assertions.assertEquals(expected, transactions);
    }

    @Test
    public void testGetAllTransactionsReturnEmptyMap() {
        transactionDAO.allTransactions = null;
        Map<Integer, List<Transaction>> transactions = transactionDAO.getAllTransactions();

        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(transactions.isEmpty());
    }

    @Test
    public void testGetTransactionsByCustomerIdReturnsCustomerTransactions() {
        Transaction t1 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 40);
        Transaction t2 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 30);
        Transaction t3 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 17);
        Transaction t4 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 48);

        // Customer 2 transactions
        Transaction t5 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 11);
        Transaction t6 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 31);
        Transaction t7 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 19);
        Transaction t8 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 29);

        transactionDAO.allTransactions = Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8);

        List<Transaction> customer1 = transactionDAO.getTransactionsByCustomerId(1);
        List<Transaction> customer2 = transactionDAO.getTransactionsByCustomerId(2);
        Assertions.assertEquals(Arrays.asList(t1, t2, t3, t4), customer1);
        Assertions.assertEquals(Arrays.asList(t5, t6, t7, t8), customer2);
    }

    @Test
    public void testGetTransactionsByCustomerIdReturnEmptyList() {
        transactionDAO.allTransactions = null;
        List<Transaction> transactions = transactionDAO.getTransactionsByCustomerId(1);

        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(transactions.isEmpty());
    }
}