package fr.lacombedulionvert.kata.domain.dateManagement;

import java.util.Objects;

public class HistoryDate {

    private static final String SEPARATOR = "/";
    private final Day day;
    private final Month month;
    private final Year year;

    HistoryDate(Day day, Month month, Year year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDate that = (HistoryDate) o;
        return Objects.equals(day, that.day) &&
                Objects.equals(month, that.month) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    public String giveDate() {
        return day.toString() + SEPARATOR + month.toString() + SEPARATOR + year.toString();
    }
}
