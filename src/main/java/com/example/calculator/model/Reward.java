package com.example.calculator.model;

import java.time.Month;
import java.util.Map;
import java.util.Objects;

public class Reward {
    private final int customerId;
    private final double totalRewards;
    private final Map<Month, Double> lastThreeMonthsRewards;

    public Reward(int customerId, Map<Month, Double> lastThreeMonthsRewards) {
        this.customerId = customerId;
        this.lastThreeMonthsRewards = lastThreeMonthsRewards;
        this.totalRewards = lastThreeMonthsRewards.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotalRewards() {
        return totalRewards;
    }

    public Map<Month, Double> getLastThreeMonthsRewards() {
        return lastThreeMonthsRewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reward reward = (Reward) o;
        return customerId == reward.customerId && Double.compare(reward.totalRewards, totalRewards) == 0 && Objects.equals(lastThreeMonthsRewards, reward.lastThreeMonthsRewards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, totalRewards, lastThreeMonthsRewards);
    }

    @Override
    public String toString() {
        return "Reward{" +
                "customerId=" + customerId +
                ", totalRewards=" + totalRewards +
                ", lastThreeMonthsRewards=" + lastThreeMonthsRewards +
                '}';
    }
}
