package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.exceptions.NegativeOrNullAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Amount {
    private BigDecimal amount;

    Amount(BigDecimal amount) {
        if (amount.doubleValue() <= 0) {
            throw new NegativeOrNullAmountException();
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_DOWN);
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
