package fr.lacombedulionvert.kata.domain;

import java.util.Objects;

public class Month {
    private int monthValue;

    Month(int monthValue) {
        this.monthValue = monthValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return monthValue == month.monthValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthValue);
    }
}
