package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.History;
import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.OperationType;
import fr.lacombedulionvert.kata.domain.history.Printer;

import java.util.List;

class Account {
    private final Amount amount;
    private final History history;

    Account() {
        this.amount = new Amount();
        this.history = new History();
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

    void printHistory(Printer printer) {
        printer.printHistory(history.showStatement());
    }

    List<HistoryLine> showHistoryLines() {
        return history.giveHistoryList();
    }

    private void generateHistoryLine(Amount amount, OperationType operationType) {
        HistoryLine historyLine = new HistoryLine.HistoryLineBuilder()
                .setOperationType(operationType)
                .setNewAmount(amount)
                .build();
        history.add(historyLine);
    }
}
