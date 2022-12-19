package com.example.calculator.service;

import com.example.calculator.dao.TransactionDAO;
import com.example.calculator.model.Reward;
import com.example.calculator.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@SpringJUnitConfig
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private TransactionDAO transactionDAO;

    @Test
    public void testGetRewardByCustomerId() {
        Transaction t1 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 80);
        Transaction t2 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 54);
        Transaction t3 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 117);
        Transaction t4 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 98);

        Mockito.when(transactionDAO.getTransactionsByCustomerId(1)).thenReturn(Arrays.asList(t1, t2, t3, t4));
        Reward reward = rewardService.getRewardByCustomerId(1);

        Map<Month, Double> threeMonthsRewards = new TreeMap<>();
        Month currentMonth = LocalDateTime.now().getMonth();
        threeMonthsRewards.put(currentMonth, 30.0);
        threeMonthsRewards.put(currentMonth.minus(1), 4.0);
        threeMonthsRewards.put(currentMonth.minus(2), 84.0);

        Reward expected = new Reward(1, threeMonthsRewards);
        Assertions.assertEquals(expected, reward);
    }

    @Test
    public void testGetRewardByCustomerIdWhenMissingTransactions() {
        Mockito.when(transactionDAO.getTransactionsByCustomerId(1)).thenReturn(Collections.emptyList());
        Reward reward = rewardService.getRewardByCustomerId(1);

        Reward expected = new Reward(1, Collections.emptyMap());
        Assertions.assertEquals(expected, reward);
    }

    @Test
    public void testGetRewards() {
        Transaction t1 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 80);
        Transaction t2 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 54);
        Transaction t3 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 117);
        Transaction t4 = new Transaction(1, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 98);

        Transaction t5 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 12, 10, 1, 45), 70);
        Transaction t6 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 11, 10, 1, 45), 94);
        Transaction t7 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 10, 10, 1, 45), 177);
        Transaction t8 = new Transaction(2, new Random().nextInt(999999999), LocalDateTime.of(2022, 9, 10, 1, 45), 68);

        Map<Integer, List<Transaction>> transactionsMap = new HashMap<>();
        transactionsMap.put(1, Arrays.asList(t1, t2, t3, t4));
        transactionsMap.put(2, Arrays.asList(t5, t6, t7, t8));
        Mockito.when(transactionDAO.getAllTransactions()).thenReturn(transactionsMap);

        List<Reward> rewards = rewardService.getRewards();

        Month currentMonth = LocalDateTime.now().getMonth();
        Map<Month, Double> customer1threeMonthsRewards = new TreeMap<>();
        customer1threeMonthsRewards.put(currentMonth, 30.0);
        customer1threeMonthsRewards.put(currentMonth.minus(1), 4.0);
        customer1threeMonthsRewards.put(currentMonth.minus(2), 84.0);
        Map<Month, Double> customer2threeMonthsRewards = new TreeMap<>();
        customer2threeMonthsRewards.put(currentMonth, 20.0);
        customer2threeMonthsRewards.put(currentMonth.minus(1), 44.0);
        customer2threeMonthsRewards.put(currentMonth.minus(2), 204.0);

        List<Reward> expected = Arrays.asList(new Reward(1, customer1threeMonthsRewards), new Reward(2, customer2threeMonthsRewards));
        Assertions.assertEquals(expected, rewards);
    }

    @Test
    public void testGetRewardsWhenMissingTransactions() {
        Mockito.when(transactionDAO.getAllTransactions()).thenReturn(Collections.emptyMap());
        List<Reward> rewards = rewardService.getRewards();

        Assertions.assertEquals(Collections.emptyList(), rewards);
    }
}