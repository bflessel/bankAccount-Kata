package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.Printer;

import java.util.List;

class Account {
    private final History history;

    Account() {
        this.history = new History();
    }

    void makeDeposit(Amount newAmount) {
        history.add(newAmount);
    }

    Amount giveActualBalance() {
        return history.giveActualBalance();
    }

    void withdrawal(Amount amount) {
        makeDeposit(amount.negateAmount());
    }

    void printHistory(Printer printer) {
        printer.printHistory(history.listHistory());
    }

    List<HistoryLine> showHistoryLines() {
        return history.giveHistory();
    }
}
