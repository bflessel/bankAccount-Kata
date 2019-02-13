package fr.lacombedulionvert.kata.domain.dateManagement;

public class HistoryDateBuilder {
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