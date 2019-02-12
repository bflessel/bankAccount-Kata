package fr.lacombedulionvert.kata.domain;

class Account {
    private Amount amount;

    Account() {
        this.amount = new Amount();
    }

    void deposit(Amount newAmount) {
        amount.add(newAmount);
    }

    Amount giveActualBalance() {
        return amount;
    }

    void withdrawal(Amount amount) {
        deposit(amount.negateAmount());
    }
}
