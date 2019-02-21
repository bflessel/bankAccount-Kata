package fr.lacombedulionvert.kata.domain.history;

import fr.lacombedulionvert.kata.domain.Amount;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;

import java.util.concurrent.atomic.DoubleAdder;

class StatementLine {

    private final Amount amount;
    private final HistoryDate date;
    private final OperationType type;

    StatementLine(Amount newAmount, HistoryDate date, OperationType type) {
        amount = newAmount;
        this.date = date;
        this.type = type;
    }

    String generateHistory(DoubleAdder total) {
        double value = amount.getValue().doubleValue();
        if (type == OperationType.WITHDRAWAL) value = -value;
        total.add(value);
        return new StringBuilder()
                .append("\t ")
                .append(date.giveDate())
                .append("\t **** ")
                .append(type)
                .append("\t")
                .append(value)
                .append("\t")
                .append("\t **** ")
                .append(total)
                .append("\n")
                .toString();
    }
}