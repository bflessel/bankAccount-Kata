package fr.lacombedulionvert.kata.domain;

import java.time.LocalDate;
import java.util.Objects;

class HistoryLine {
    private HistoryDate date;
    private Amount newAmount;
    private OperationType deposit;

    HistoryLine(HistoryDate date, Amount newAmount, OperationType deposit) {

        this.date = date;
        this.newAmount = newAmount;
        this.deposit = deposit;
    }

    HistoryLine(Amount newAmount, OperationType deposit) {
        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        this.date = date;
        this.newAmount = newAmount;
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryLine that = (HistoryLine) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(newAmount, that.newAmount) &&
                deposit == that.deposit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, newAmount, deposit);
    }

    @Override
    public String toString() {
        return "HistoryLine{" +
                "date=" + date +
                ", newAmount=" + newAmount +
                ", deposit=" + deposit +
                '}';
    }
}
