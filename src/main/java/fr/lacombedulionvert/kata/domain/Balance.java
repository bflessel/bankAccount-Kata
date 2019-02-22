package fr.lacombedulionvert.kata.domain;

import java.math.BigDecimal;
import java.util.Objects;

class Balance {
    private BigDecimal amount;
    private final InformationProvider informationProvider;

    Balance() {
        this.amount = BigDecimal.ZERO;
        this.informationProvider = new SimpleInformationProvider();
    }

    void add(Amount operationAmount) {
        amount = amount.add(operationAmount.getValue());
    }

    public BigDecimal getValue() {
        return amount;
    }

    void takeOff(Amount otherAmount) {
        amount = amount.subtract(otherAmount.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    boolean isPossible(Amount otherAmount) {
        return amount.subtract(otherAmount.getValue()).compareTo(informationProvider.giveOverdraftLimit()) <= 0;
    }
}
