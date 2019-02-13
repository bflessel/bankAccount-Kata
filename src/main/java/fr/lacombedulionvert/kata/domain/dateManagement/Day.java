package fr.lacombedulionvert.kata.domain.dateManagement;

import java.util.Objects;

public class Day {
    private final int dayOfMonth;

    public Day(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return dayOfMonth == day.dayOfMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfMonth);
    }

    @Override
    public String toString() {
        return String.valueOf(dayOfMonth);
    }
}
