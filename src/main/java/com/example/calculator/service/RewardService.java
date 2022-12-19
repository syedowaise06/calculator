package com.example.calculator.service;

import com.example.calculator.dao.TransactionDAO;
import com.example.calculator.model.Reward;
import com.example.calculator.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RewardService {

    @Autowired
    private TransactionDAO transactionDAO;

    public Reward getRewardByCustomerId(int customerId) {
        List<Transaction> transactionList = transactionDAO.getTransactionsByCustomerId(customerId);
        Map<Month, Double> lastThreeMonthsRewards = getLastThreeMonthsRewards(transactionList);
        return new Reward(customerId, lastThreeMonthsRewards);
    }

    public List<Reward> getRewards() {
        Map<Integer, List<Transaction>> transactionsMap = transactionDAO.getAllTransactions();
        List<Reward> rewards = new ArrayList<>();

        transactionsMap.forEach((customerId, transactions) -> {
            Map<Month, Double> lastThreeMonthsRewards = getLastThreeMonthsRewards(transactions);
            rewards.add(new Reward(customerId, lastThreeMonthsRewards));
        });

        return rewards;
    }

    private Map<Month, Double> getLastThreeMonthsRewards(List<Transaction> transactionList) {
        Map<Month, Double> reward = new TreeMap<>();
        int currentMonth = LocalDate.now().getMonthValue();

        for (Transaction txn : transactionList) {
            if (currentMonth - txn.getTransactionDateTime().getMonthValue() <= 2) {
                Month txnMonth = txn.getTransactionDateTime().getMonth();
                double amount = reward.getOrDefault(txnMonth, 0.0);
                reward.put(txnMonth, amount + calculateRewardsAmount(txn.getTransactionAmount()));
            }
        }
        return reward;
    }

    private double calculateRewardsAmount(double txnAmount) {
        if (txnAmount > 100) {
            return 50 + 2 * (txnAmount - 100);
        } else if (txnAmount > 50) {
            return txnAmount - 50;
        } else {
            return 0;
        }
    }
}
