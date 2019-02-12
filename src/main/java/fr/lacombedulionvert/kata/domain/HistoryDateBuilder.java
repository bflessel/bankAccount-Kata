package fr.lacombedulionvert.kata.domain;

class HistoryDateBuilder {
    private Day day;
    private Month month;
    private Year year;

    HistoryDateBuilder setDay(Day day) {
        this.day = day;
        return this;
    }

    HistoryDateBuilder setMonth(Month month) {
        this.month = month;
        return this;
    }

    HistoryDateBuilder setYear(Year year) {
        this.year = year;
        return this;
    }

    HistoryDate createHistoryDate() {
        return new HistoryDate(day, month, year);
    }
}