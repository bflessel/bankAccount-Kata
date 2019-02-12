package fr.lacombedulionvert.kata.domain;

import java.util.List;

class Account {
    private TotalSavings totalSavings;

    Account() {
        this.totalSavings = new TotalSavings();
    }

    void deposit(Amount newAmount) {
        totalSavings.add(newAmount);
    }

    Amount giveActualBalance() {
        return totalSavings.givezActualBalance();
    }

    void withdrawal(Amount amount) {
        deposit(amount.negateAmount());
    }

    void printHistory(Printer printer) {

    }

    List<HistoryLine> showHistoryLines() {
        return totalSavings.giveHistory();
    }
}
