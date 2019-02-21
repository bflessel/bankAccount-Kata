package fr.lacombedulionvert.kata.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Amount {
    private BigDecimal amount;

    Amount() {
        amount = BigDecimal.valueOf(0);
    }

    Amount(BigDecimal amount) {
        if (amount.doubleValue() <= 0) {
            throw new UnsupportedOperationException();
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_DOWN);
    }

    void add(Amount newAmount) {
        amount = amount.add(newAmount.amount);
    }

    void takeOff(Amount newAmount) {
        amount = amount.subtract(newAmount.amount);
    }


    boolean isNegative() {
        return amount.intValue() < 0;
    }

    public BigDecimal getValue() {
        return amount;
    }

    Amount minus(Amount operationAmount) {
        return new Amount(operationAmount.amount.subtract(amount));
    }

    Amount plus(Amount operationAmount) {
        return new Amount(operationAmount.amount.add(amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Objects.equals(amount, amount1.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
