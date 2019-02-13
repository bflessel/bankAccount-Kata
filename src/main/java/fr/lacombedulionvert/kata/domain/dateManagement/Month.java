package fr.lacombedulionvert.kata.domain.dateManagement;

import java.util.Objects;

public class Month {
    private final int monthValue;

    public Month(int monthValue) {
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

    @Override
    public String toString() {
        return String.valueOf(monthValue);
    }
}
