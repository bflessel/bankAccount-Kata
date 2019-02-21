package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.HistoryLineBuilder;
import fr.lacombedulionvert.kata.domain.history.HistoryLines;
import fr.lacombedulionvert.kata.domain.history.OperationType;

import java.util.List;

class History {
    private final Amount amount;
    private final HistoryLines historyLines;

    History() {
        this.amount = new Amount();
        this.historyLines = new HistoryLines();
    }

    Amount giveBalance() {
        return amount;
    }

    void makeDeposit(Amount operationAmount) {
        OperationType operationType = OperationType.DEPOSIT;
        generateHistoryLine(operationAmount, operationType);
        amount.add(operationAmount);
    }

    void makeWithdrawal(Amount otherAmount) {
        OperationType operationType = OperationType.WITHDRAWAL;
        Amount newAmount = otherAmount.minus(amount);
        if (newAmount.isNegative()) {
            throw new UnsupportedOperationException();
        }
        generateHistoryLine(otherAmount, operationType);
        this.amount.takeOff(otherAmount);

    }

    private void generateHistoryLine(Amount amount, OperationType operationType) {
        HistoryLine historyLine = new HistoryLineBuilder()
                .setOperationType(operationType)
                .setNewAmount(amount)
                .createActualHistoryLine();
        historyLines.add(historyLine);
    }

    List<HistoryLine> giveHistory() {
        return historyLines.giveHistoryList();
    }

    List<String> listHistory() {
        return historyLines.showStatement();

    }
}
