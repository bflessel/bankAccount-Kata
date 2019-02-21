package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.History;
import fr.lacombedulionvert.kata.domain.history.Operation;
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
        generateOperation(operationAmount, operationType);
        amount.add(operationAmount);
    }

    void makeWithdrawal(Amount otherAmount) {
        OperationType operationType = OperationType.WITHDRAWAL;
        Amount newAmount = otherAmount.minus(amount);
        if (newAmount.isNegative()) {
            throw new UnsupportedOperationException();
        }
        generateOperation(otherAmount, operationType);
        this.amount.takeOff(otherAmount);
    }

    void printHistory(Printer printer) {
        printer.printHistory(history.showStatement());
    }

    List<Operation> showOperations() {
        return history.giveOperations();
    }

    private void generateOperation(Amount amount, OperationType operationType) {
        Operation operation = new Operation.OperationBuilder()
                .setOperationType(operationType)
                .setNewAmount(amount)
                .build();
        history.add(operation);
    }
}
