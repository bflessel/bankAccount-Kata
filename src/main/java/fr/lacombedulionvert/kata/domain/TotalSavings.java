package fr.lacombedulionvert.kata.domain;

import java.util.List;

class TotalSavings {
    private Amount amount;
    private Histories histories;

    void add(Amount newAmount) {
        this.amount.add(newAmount);
        HistoryLine historyLine = new HistoryLineBuilder().setDeposit(OperationType.DEPOSIT).setNewAmount(newAmount).createActualHistoryLine();
        histories.add(historyLine);
    }

    TotalSavings() {
        this.amount = new Amount();
        this.histories = new Histories();
    }

    Amount givezActualBalance() {
        return amount;
    }

    List<HistoryLine> giveHistory() {
        return histories.giveHistoryList();
    }
}
