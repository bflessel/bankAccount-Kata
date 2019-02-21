package fr.lacombedulionvert.kata.domain.dateManagement;

import java.util.Objects;

public class HistoryDate {

    private static final String SEPARATOR = "/";
    private final Day day;
    private final Month month;
    private final Year year;

    private HistoryDate(Day day, Month month, Year year) {
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

    public static class HistoryDateBuilder {
        private Day day;
        private Month month;
        private Year year;

        public HistoryDateBuilder setDay(Day day) {
            this.day = day;
            return this;
        }

        public HistoryDateBuilder setMonth(Month month) {
            this.month = month;
            return this;
        }

        public HistoryDateBuilder setYear(Year year) {
            this.year = year;
            return this;
        }

        public HistoryDate createHistoryDate() {
            return new HistoryDate(day, month, year);
        }
    }
}
