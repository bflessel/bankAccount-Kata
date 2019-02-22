package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.exceptions.NotEnoughFoundsException;
import fr.lacombedulionvert.kata.domain.history.History;
import fr.lacombedulionvert.kata.domain.history.Operation;
import fr.lacombedulionvert.kata.domain.history.OperationType;
import fr.lacombedulionvert.kata.domain.history.Printer;

import java.util.List;

class Account {
    private final Balance amount;
    private final History history;

    Account() {
        this.amount = new Balance();
        this.history = new History();
    }

    Balance giveBalance() {
        return amount;
    }

    void makeDeposit(Amount operationAmount) {
        OperationType operationType = OperationType.DEPOSIT;
        generateOperation(operationAmount, operationType);
        amount.add(operationAmount);
    }

    void makeWithdrawal(Amount otherAmount) {
        OperationType operationType = OperationType.WITHDRAWAL;
        if (amount.isPossible(otherAmount)) {
            throw new NotEnoughFoundsException();
        }
        generateOperation(otherAmount, operationType);
        amount.takeOff(otherAmount);
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
