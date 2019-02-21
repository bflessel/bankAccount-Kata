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
        history.makeDeposit(newAmount);
    }

    Amount giveBalance() {
        return history.giveBalance();
    }

    void makeWithdrawal(Amount amount) {
        history.makeWithdrawal(amount);
    }

    void printHistory(Printer printer) {
        printer.printHistory(history.listHistory());
    }

    List<HistoryLine> showHistoryLines() {
        return history.giveHistory();
    }
}
