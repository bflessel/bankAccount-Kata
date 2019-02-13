package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.Printer;

import java.util.List;

class Account {
    private final TotalSavings totalSavings;

    Account() {
        this.totalSavings = new TotalSavings();
    }

    void deposit(Amount newAmount) {
        totalSavings.add(newAmount);
    }

    Amount giveActualBalance() {
        return totalSavings.giveActualBalance();
    }

    void withdrawal(Amount amount) {
        deposit(amount.negateAmount());
    }

    void printHistory(Printer printer) {
        printer.printHistory(totalSavings.listHistory());
    }

    List<HistoryLine> showHistoryLines() {
        return totalSavings.giveHistory();
    }
}
