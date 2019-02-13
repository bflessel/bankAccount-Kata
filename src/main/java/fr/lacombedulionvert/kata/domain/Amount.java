package fr.lacombedulionvert.kata.domain;

import java.util.Objects;

public class Amount {
    private double amount;

    Amount() {
        amount = 0.0;
    }

    Amount(double amount) {
        this.amount = amount;
    }

    void add(Amount newAmount) {
        amount += newAmount.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Double.compare(amount1.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    Amount negateAmount() {
        return new Amount(-amount);
    }

    boolean isNegative() {
        return amount < 0;
    }

    public double getValue() {
        return amount;
    }
}
