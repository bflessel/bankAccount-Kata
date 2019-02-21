package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import fr.lacombedulionvert.kata.domain.dateManagement.Day;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;
import fr.lacombedulionvert.kata.domain.dateManagement.Month;
import fr.lacombedulionvert.kata.domain.dateManagement.Year;
import fr.lacombedulionvert.kata.domain.history.HistoryLine;
import fr.lacombedulionvert.kata.domain.history.OperationType;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class AccountTest {

    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_amount_of_the_deposit(@InRange(minDouble = 1) double amount) {
        Account account = new Account();
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));
        Amount totalSavings = account.giveBalance();

        account.makeDeposit(newAmount);

        assertThat(newAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_deposits_on_an_empty_should_give_the_amount_of_the_sum_of_the_deposits(@InRange(minDouble = 1) double amount, @InRange(minDouble = 1) double otherAmount, @InRange(minDouble = 1) double thirdAmount) {
        Account account = new Account();
        account.makeDeposit(new Amount(BigDecimal.valueOf(amount)));
        account.makeDeposit(new Amount(BigDecimal.valueOf(otherAmount)));
        account.makeDeposit(new Amount(BigDecimal.valueOf(thirdAmount)));

        Amount totalSavings = account.giveBalance();

        Amount realAmount = new Amount(BigDecimal.valueOf(amount + otherAmount + thirdAmount));
        assertThat(realAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_making_a_withdrawal_on_account_with_10000_should_return_the_sub_of_10000(@InRange(minDouble = 1, maxDouble = 999) double amount) {
        Account account = new Account();
        Amount thousandAmount = new Amount(BigDecimal.valueOf(10000.0));
        Amount realAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(thousandAmount);
        account.makeWithdrawal(realAmount);
        Amount totalSavings = account.giveBalance();

        Amount expectedResult = realAmount.minus(thousandAmount);
        assertThat(expectedResult).isEqualTo(totalSavings);
    }


    @Property
    public void after_making_a_withdrawal_on_empty_account_should_throw_an_exception(@InRange(minDouble = 1) double amount) {
        Account account = new Account();
        UnsupportedOperationException exception = null;
        try {
            account.makeWithdrawal(new Amount(BigDecimal.valueOf(amount)));
        } catch (UnsupportedOperationException e) {
            exception = e;
        }
        assertThat(exception).isNotEqualTo(null);
    }

    @Property
    public void after_an_amount_of_0_should_throw_an_exception() {
        UnsupportedOperationException exception = null;
        try {
            new Amount(BigDecimal.valueOf(0.0));
        } catch (UnsupportedOperationException e) {
            exception = e;
        }
        assertThat(exception).isNotEqualTo(null);
    }


    @Property(trials = 25)
    public void after_multiple_withdrawals_on_an_account_with_1000000_should_give_the_amount_1000000_minus_the_substraction_of_the_withdrawals(@InRange(minDouble = 1, maxDouble = 9999) double amount, @InRange(minDouble = 1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 1, maxDouble = 9999) double thirdAmount) {
        Account account = new Account();
        Amount initialDeposit = new Amount(BigDecimal.valueOf(1000000.0));
        Amount firstOperation = new Amount(BigDecimal.valueOf(amount));
        Amount secondOperation = new Amount(BigDecimal.valueOf(otherAmount));
        Amount thirdOperation = new Amount(BigDecimal.valueOf(thirdAmount));

        account.makeDeposit(initialDeposit);
        account.makeWithdrawal(firstOperation);
        account.makeWithdrawal(secondOperation);
        account.makeWithdrawal(thirdOperation);

        Amount totalSavings = account.giveBalance();

        Amount expectedAmount = thirdOperation.minus(secondOperation.minus(firstOperation.minus(initialDeposit)));
        assertThat(expectedAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_withdrawals_and_deposit_on_an_empty_should_give_the_amount_of_the_sum_of_all(@InRange(minDouble = 1, maxDouble = 9999) double amount, @InRange(minDouble = 1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 1, maxDouble = 9999) double thirdAmount) {
        Account account = new Account();
        Amount initialDeposit = new Amount(BigDecimal.valueOf(1000000.0));
        Amount firstOperation = new Amount(BigDecimal.valueOf(amount));
        Amount secondOperation = new Amount(BigDecimal.valueOf(otherAmount));
        Amount thirdOperation = new Amount(BigDecimal.valueOf(thirdAmount));

        account.makeDeposit(initialDeposit);
        account.makeDeposit(firstOperation);
        account.makeWithdrawal(secondOperation);
        account.makeDeposit(thirdOperation);
        Amount totalSavings = account.giveBalance();

        Amount expectedAmount = thirdOperation.plus(secondOperation.minus(firstOperation.plus(initialDeposit)));
        assertThat(expectedAmount).isEqualTo(totalSavings);
    }


    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_history_of_the_deposit(double amount) {
        Account account = new Account();
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(newAmount);
        List<HistoryLine> lines = account.showHistoryLines();

        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        HistoryLine historyLine = new HistoryLine.HistoryLineBuilder()
                .setDate(date)
                .setNewAmount(newAmount)
                .setOperationType(OperationType.DEPOSIT)
                .createHistoryLine();
        HistoryLine realHistory = lines.get(0);

        assertThat(historyLine).isEqualTo(realHistory);
    }

    @Property
    public void after_making_a_withdrawal_on_an__account_should_give_the_history_of_the_deposit(@InRange(minDouble = 1) double amount) {
        Account account = new Account();
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));
        Amount initialDeposit = new Amount(BigDecimal.valueOf(1000000.0));

        account.makeDeposit(initialDeposit);
        account.makeWithdrawal(newAmount);
        List<HistoryLine> lines = account.showHistoryLines();

        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        HistoryLine historyLine = new HistoryLine.HistoryLineBuilder()
                .setDate(date)
                .setNewAmount(newAmount)
                .setOperationType(OperationType.WITHDRAWAL)
                .createHistoryLine();
        HistoryLine realHistory = lines.get(1);
        assertThat(historyLine).isEqualTo(realHistory);
    }

    @Test
    public void given_a_deposit_should_print_a_line() {
        Account account = new Account();
        account.makeDeposit(new Amount(BigDecimal.valueOf(300.0)));
        StringPrinter printer = new StringPrinter();

        account.printHistory(printer);
        String history = printer.showOutPut();

        String expectedResumeOfOperations = createALineForADeposit();
        assertThat(expectedResumeOfOperations).isEqualTo(history);
    }

    private String createALineForADeposit() {
        LocalDate actualDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(actualDate.getDayOfMonth())).setMonth(new Month(actualDate.getMonthValue())).setYear(new Year(actualDate.getYear())).createHistoryDate();
        return "\t " + date.giveDate() + "\t **** DEPOSIT\t300.0\t\t **** 300.0\n";
    }
}
