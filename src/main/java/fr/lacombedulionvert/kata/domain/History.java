package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.HistorieLines;
import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.HistoryLineBuilder;
import fr.lacombedulionvert.kata.domain.history.OperationType;

import java.util.List;

class History {
    private Amount amount;
    private HistorieLines historieLines;

    void add(Amount operationAmount) {

        OperationType operationType = OperationType.DEPOSIT;
        Amount newAmount = amount.plus(operationAmount);
        if (operationAmount.isEqualZero() || newAmount.isNegative()) {
            throw new UnsupportedOperationException();
        }
        if (operationAmount.isNegative()) {
            operationType = OperationType.WITHDRAWAL;
        }
        HistoryLine historyLine = new HistoryLineBuilder()
                .setOperationType(operationType)
                .setNewAmount(operationAmount)
                .createActualHistoryLine();
        historieLines.add(historyLine);
        this.amount.add(operationAmount);
    }

    History() {
        this.amount = new Amount();
        this.historieLines = new HistorieLines();
    }

    Amount giveActualBalance() {
        return amount;
    }

    List<HistoryLine> giveHistory() {
        return historieLines.giveHistoryList();
    }

    List<String> listHistory() {
        return historieLines.showHistory();

    }
}
