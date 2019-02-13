package fr.lacombedulionvert.kata.domain.history;

import fr.lacombedulionvert.kata.domain.Amount;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;

public class HistoryLineBuilder {
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

    public HistoryLine createActualHistoryLine() {
        return new HistoryLine(newAmount, deposit);
    }
}