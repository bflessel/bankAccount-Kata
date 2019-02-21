package fr.lacombedulionvert.kata.domain.history;

import fr.lacombedulionvert.kata.domain.Amount;
import fr.lacombedulionvert.kata.domain.dateManagement.Day;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;
import fr.lacombedulionvert.kata.domain.dateManagement.Month;
import fr.lacombedulionvert.kata.domain.dateManagement.Year;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {
    private final HistoryDate date;
    private final Amount newAmount;
    private final OperationType type;

    private Operation(HistoryDate date, Amount newAmount, OperationType type) {

        this.date = date;
        this.newAmount = newAmount;
        this.type = type;
    }

    private Operation(Amount newAmount, OperationType type) {
        LocalDate localDate = LocalDate.now();
        this.date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        this.newAmount = newAmount;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
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
        return "Operation{" +
                "date=" + date +
                ", newAmount=" + newAmount +
                ", type=" + type +
                '}';
    }


    StatementLine generateStatementLine() {
        return new StatementLine(newAmount, date, type);

    }

    public static class OperationBuilder {
        private HistoryDate date;
        private Amount newAmount;
        private OperationType deposit;

        public OperationBuilder setDate(HistoryDate date) {
            this.date = date;
            return this;
        }

        public OperationBuilder setNewAmount(Amount newAmount) {
            this.newAmount = newAmount;
            return this;
        }

        public OperationBuilder setOperationType(OperationType deposit) {
            this.deposit = deposit;
            return this;
        }

        public Operation createHistoryLine() {
            return new Operation(date, newAmount, deposit);
        }

        public Operation build() {
            return new Operation(newAmount, deposit);
        }
    }
}

