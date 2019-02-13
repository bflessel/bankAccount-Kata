package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.Histories;
import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.HistoryLineBuilder;
import fr.lacombedulionvert.kata.domain.history.OperationType;

import java.util.List;

class TotalSavings {
    private Amount amount;
    private Histories histories;

    void add(Amount newAmount) {
        this.amount.add(newAmount);
        OperationType operationType = OperationType.DEPOSIT;
        if (newAmount.isNegative())
            operationType = OperationType.WITHDRAWAL;
        HistoryLine historyLine = new HistoryLineBuilder()
                .setOperationType(operationType)
                .setNewAmount(newAmount)
                .createActualHistoryLine();
        histories.add(historyLine);
    }

    TotalSavings() {
        this.amount = new Amount();
        this.histories = new Histories();
    }

    Amount giveActualBalance() {
        return amount;
    }

    List<HistoryLine> giveHistory() {
        return histories.giveHistoryList();
    }

    public List<String> listHistory() {
        return histories.showHistory();

    }
}
