package fr.lacombedulionvert.kata.domain.history;

import fr.lacombedulionvert.kata.domain.Amount;
import fr.lacombedulionvert.kata.domain.dateManagement.Day;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;
import fr.lacombedulionvert.kata.domain.dateManagement.Month;
import fr.lacombedulionvert.kata.domain.dateManagement.Year;

import java.time.LocalDate;
import java.util.Objects;

public class HistoryLine {
    private final HistoryDate date;
    private final Amount newAmount;
    private final OperationType type;

    private HistoryLine(HistoryDate date, Amount newAmount, OperationType type) {

        this.date = date;
        this.newAmount = newAmount;
        this.type = type;
    }

    private HistoryLine(Amount newAmount, OperationType type) {
        LocalDate localDate = LocalDate.now();
        this.date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
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

    public static class HistoryLineBuilder {
        private HistoryDate date;
        private Amount newAmount;
        private OperationType deposit;

        public HistoryLineBuilder setDate(HistoryDate date) {
            this.date = date;
            return this;
        }

        public HistoryLineBuilder setNewAmount(Amount newAmount) {
            this.newAmount = newAmount;
            return this;
        }

        public HistoryLineBuilder setOperationType(OperationType deposit) {
            this.deposit = deposit;
            return this;
        }

        public HistoryLine createHistoryLine() {
            return new HistoryLine(date, newAmount, deposit);
        }

        public HistoryLine build() {
            return new HistoryLine(newAmount, deposit);
        }
    }
}

