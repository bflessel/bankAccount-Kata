package fr.lacombedulionvert.kata.domain.history;

import fr.lacombedulionvert.kata.domain.Amount;
import fr.lacombedulionvert.kata.domain.dateManagement.*;

import java.time.LocalDate;
import java.util.Objects;

public class HistoryLine {
    private final HistoryDate date;
    private final Amount newAmount;
    private final OperationType type;

    HistoryLine(HistoryDate date, Amount newAmount, OperationType type) {

        this.date = date;
        this.newAmount = newAmount;
        this.type = type;
    }

    HistoryLine(Amount newAmount, OperationType type) {
        LocalDate localDate = LocalDate.now();
        this.date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        this.newAmount = newAmount;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryLine that = (HistoryLine) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(newAmount, that.newAmount) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, newAmount, type);
    }

    @Override
    public String toString() {
        return "HistoryLine{" +
                "date=" + date +
                ", newAmount=" + newAmount +
                ", type=" + type +
                '}';
    }


    StatementLine generateStatementLine() {
        return new StatementLine(newAmount, date, type);

    }

}

