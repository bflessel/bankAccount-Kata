package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import fr.lacombedulionvert.kata.domain.dateManagement.*;
import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.HistoryLineBuilder;
import fr.lacombedulionvert.kata.domain.history.OperationType;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class AccountTest {

    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_amount_of_the_deposit(Double amount) {
        Account account = new Account();
        account.makeDeposit(new Amount(amount));
        Amount totalSavings = account.giveBalance();
        assertThat(new Amount(amount)).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_deposits_on_an_empty_should_give_the_amount_of_the_sum_of_the_deposits(Double amount, Double otherAmount, Double thirdAmount) {
        Account account = new Account();
        account.makeDeposit(new Amount(amount));
        account.makeDeposit(new Amount(otherAmount));
        account.makeDeposit(new Amount(thirdAmount));
        Amount totalSavings = account.giveBalance();
        Amount realAmount = new Amount(amount + otherAmount + thirdAmount);
        assertThat(realAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_making_a_withdrawal_on_account_with_10000_should_return_the_sub_of_10000(@InRange(minDouble = 0.1, maxDouble = 9999) double amount) {
        Account account = new Account();
        account.makeDeposit(new Amount(10000.0));
        account.makeWithdrawal(new Amount(amount));
        Amount totalSavings = account.giveBalance();
        assertThat(new Amount(10000.0 - amount)).isEqualTo(totalSavings);
    }


    @Property
    public void after_making_a_withdrawal_on_empty_account_should_throw_an_exception(@InRange(minDouble = 0.1) double amount) {
        Account account = new Account();
        UnsupportedOperationException exception = null;
        try {
            account.makeWithdrawal(new Amount(amount));
        } catch (UnsupportedOperationException e) {
            exception = e;
        }
        assertThat(exception).isNotEqualTo(null);
    }

    @Property
    public void after_an_amount_of_0_should_throw_an_exception() {
        Account account = new Account();
        UnsupportedOperationException exception = null;
        try {
            account.makeWithdrawal(new Amount(0.0));
        } catch (UnsupportedOperationException e) {
            exception = e;
        }
        assertThat(exception).isNotEqualTo(null);
    }


    @Property(trials = 25)
    public void after_multiple_withdrawals_on_an_account_with_1000000_should_give_the_amount_1000000_minus_the_substraction_of_the_withdrawals(@InRange(minDouble = 0.1, maxDouble = 9999) double amount, @InRange(minDouble = 0.1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 0.1, maxDouble = 9999) double thirdAmount) {
        Account account = new Account();
        account.makeDeposit(new Amount(1000000.0));
        account.makeWithdrawal(new Amount(amount));
        account.makeWithdrawal(new Amount(otherAmount));
        account.makeWithdrawal(new Amount(thirdAmount));
        Amount totalSavings = account.giveBalance();
        Amount realAmount = new Amount(1000000.0 - amount - otherAmount - thirdAmount);
        assertThat(realAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_withdrawals_and_deposit_on_an_empty_should_give_the_amount_of_the_sum_of_all(@InRange(minDouble = 0.1, maxDouble = 9999) double amount, @InRange(minDouble = 0.1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 0.1, maxDouble = 9999) double thirdAmount) {
        Account account = new Account();
        account.makeDeposit(new Amount(1000000.0));
        account.makeDeposit(new Amount(amount));
        account.makeWithdrawal(new Amount(otherAmount));
        account.makeDeposit(new Amount(thirdAmount));
        Amount totalSavings = account.giveBalance();
        Amount realAmount = new Amount(1000000.0 + amount - otherAmount + thirdAmount);
        assertThat(realAmount).isEqualTo(totalSavings);
    }


    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_history_of_the_deposit(Double amount) {
        Account account = new Account();
        Amount newAmount = new Amount(amount);
        account.makeDeposit(newAmount);
        List<HistoryLine> lines = account.showHistoryLines();
        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        HistoryLine historyLine = new HistoryLineBuilder()
                .setDate(date)
                .setNewAmount(newAmount)
                .setOperationType(OperationType.DEPOSIT)
                .createHistoryLine();
        HistoryLine realHistory = lines.get(0);
        assertThat(historyLine).isEqualTo(realHistory);
    }

    @Property
    public void after_making_a_withdrawal_on_an__account_should_give_the_history_of_the_deposit(Double amount) {
        Account account = new Account();
        account.makeDeposit(new Amount(1000000.0));
        Amount newAmount = new Amount(amount);
        Amount expectedAmount = new Amount(1000000.0 - amount);
        account.makeWithdrawal(newAmount);
        List<HistoryLine> lines = account.showHistoryLines();
        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        HistoryLine historyLine = new HistoryLineBuilder()
                .setDate(date)
                .setNewAmount(newAmount.negateAmount())
                .setOperationType(OperationType.WITHDRAWAL)
                .createHistoryLine();
        HistoryLine realHistory = lines.get(1);
        assertThat(historyLine).isEqualTo(realHistory);
    }

    @Test
    public void given_a_deposit_should_print_a_line() {
        Account account = new Account();
        account.makeDeposit(new Amount(300.0));
        StringPrinter printer = new StringPrinter();
        account.printHistory(printer);
        String history = printer.showOutPut();
        String expectedResumeOfOperations = createALineForADeposit();
        assertThat(expectedResumeOfOperations).isEqualTo(history);
    }

    private String createALineForADeposit() {
        LocalDate actualDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(actualDate.getDayOfMonth())).setMonth(new Month(actualDate.getMonthValue())).setYear(new Year(actualDate.getYear())).createHistoryDate();
        return "\t " + date.giveDate() + "\t **** DEPOSIT\t300.0\t\t **** 300.0\n";
    }
}
