package fr.lacombedulionvert.kata.domain;

class HistoryLineBuilder {
    private HistoryDate date;
    private Amount newAmount;
    private OperationType deposit;

    HistoryLineBuilder setDate(HistoryDate date) {
        this.date = date;
        return this;
    }

    HistoryLineBuilder setNewAmount(Amount newAmount) {
        this.newAmount = newAmount;
        return this;
    }

    HistoryLineBuilder setDeposit(OperationType deposit) {
        this.deposit = deposit;
        return this;
    }

    HistoryLine createHistoryLine() {
        return new HistoryLine(date, newAmount, deposit);
    }

    HistoryLine createActualHistoryLine() {
        return new HistoryLine(newAmount, deposit);
    }
}