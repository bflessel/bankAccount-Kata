package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import fr.lacombedulionvert.kata.domain.dateManagement.Day;
import fr.lacombedulionvert.kata.domain.dateManagement.HistoryDate;
import fr.lacombedulionvert.kata.domain.dateManagement.Month;
import fr.lacombedulionvert.kata.domain.dateManagement.Year;
import fr.lacombedulionvert.kata.domain.exceptions.NotEnoughFoundsException;
import fr.lacombedulionvert.kata.domain.history.Operation;
import fr.lacombedulionvert.kata.domain.history.OperationType;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class AccountTest {

    private Account account;
    private Amount initialDeposit;

    @Before
    public void init() {
        account = new Account();
        initialDeposit = new Amount(BigDecimal.valueOf(1000000.0));
    }

    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_amount_of_the_deposit(@InRange(minDouble = 0.1, maxDouble = 10000000000000.0) double amount) {
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(newAmount);
        Balance totalSavings = account.giveBalance();

        Balance newBalance = new Balance();
        newBalance.add(newAmount);
        assertThat(totalSavings).isEqualTo(newBalance);
    }

    @Property
    public void after_multiple_deposits_on_an_empty_should_give_the_amount_of_the_sum_of_the_deposits(@InRange(minDouble = 1) double amount, @InRange(minDouble = 1) double otherAmount, @InRange(minDouble = 1) double thirdAmount) {
        account.makeDeposit(new Amount(BigDecimal.valueOf(amount)));
        account.makeDeposit(new Amount(BigDecimal.valueOf(otherAmount)));
        account.makeDeposit(new Amount(BigDecimal.valueOf(thirdAmount)));

        Balance totalSavings = account.giveBalance();
        Balance realAmount = new Balance();
        realAmount.add(new Amount(BigDecimal.valueOf(amount + otherAmount + thirdAmount)));
        assertThat(totalSavings).isEqualTo(realAmount);
    }

    @Property
    public void after_making_a_withdrawal_on_account_with_10000_should_return_the_sub_of_10000(@InRange(minDouble = 1, maxDouble = 999) double amount) {
        Amount realAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(initialDeposit);
        account.makeWithdrawal(realAmount);
        Balance totalSavings = account.giveBalance();

        Balance expectedResult = new Balance();
        expectedResult.add(realAmount.minus(initialDeposit));
        assertThat(totalSavings).isEqualTo(expectedResult);
    }


    @Property
    public void after_making_a_withdrawal_on_empty_account_should_throw_an_exception(@InRange(minDouble = 1) double amount) {
        Assertions.assertThatThrownBy(() -> account.makeWithdrawal(new Amount(BigDecimal.valueOf(amount))))
                .isInstanceOf(NotEnoughFoundsException.class);
    }



    @Property(trials = 25)
    public void after_multiple_withdrawals_on_an_account_with_1000000_should_give_the_amount_1000000_minus_the_substraction_of_the_withdrawals(@InRange(minDouble = 1, maxDouble = 9999) double amount, @InRange(minDouble = 1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 1, maxDouble = 9999) double thirdAmount) {
        Amount firstOperation = new Amount(BigDecimal.valueOf(amount));
        Amount secondOperation = new Amount(BigDecimal.valueOf(otherAmount));
        Amount thirdOperation = new Amount(BigDecimal.valueOf(thirdAmount));

        account.makeDeposit(initialDeposit);
        account.makeWithdrawal(firstOperation);
        account.makeWithdrawal(secondOperation);
        account.makeWithdrawal(thirdOperation);

        Balance totalSavings = account.giveBalance();

        Balance expectedAmount = new Balance();
        expectedAmount.add(thirdOperation.minus(secondOperation.minus(firstOperation.minus(initialDeposit))));
        assertThat(totalSavings).isEqualTo(expectedAmount);
    }

    @Property
    public void after_multiple_withdrawals_and_deposit_on_an_empty_should_give_the_amount_of_the_sum_of_all(@InRange(minDouble = 1, maxDouble = 9999) double amount, @InRange(minDouble = 1, maxDouble = 9999) double otherAmount, @InRange(minDouble = 1, maxDouble = 9999) double thirdAmount) {
        Amount firstOperation = new Amount(BigDecimal.valueOf(amount));
        Amount secondOperation = new Amount(BigDecimal.valueOf(otherAmount));
        Amount thirdOperation = new Amount(BigDecimal.valueOf(thirdAmount));

        account.makeDeposit(initialDeposit);
        account.makeDeposit(firstOperation);
        account.makeWithdrawal(secondOperation);
        account.makeDeposit(thirdOperation);
        Balance totalSavings = account.giveBalance();

        Balance expectedAmount = new Balance();
        expectedAmount.add(thirdOperation.plus(secondOperation.minus(firstOperation.plus(initialDeposit))));
        assertThat(totalSavings).isEqualTo(expectedAmount);
    }


    @Property
    public void history_should_contain_initial_deposit(double amount) {
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(newAmount);
        List<Operation> lines = account.showOperations();

        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        Operation operation = new Operation.OperationBuilder()
                .setDate(date)
                .setNewAmount(newAmount)
                .setOperationType(OperationType.DEPOSIT)
                .createHistoryLine();
        Operation realHistory = lines.get(0);

        assertThat(realHistory).isEqualTo(operation);
    }

    @Property
    public void after_making_a_withdrawal_on_an__account_should_give_the_history_of_the_deposit(@InRange(minDouble = 1) double amount) {
        Amount newAmount = new Amount(BigDecimal.valueOf(amount));

        account.makeDeposit(initialDeposit);
        account.makeWithdrawal(newAmount);
        List<Operation> lines = account.showOperations();

        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        Operation operation = new Operation.OperationBuilder()
                .setDate(date)
                .setNewAmount(newAmount)
                .setOperationType(OperationType.WITHDRAWAL)
                .createHistoryLine();
        Operation realHistory = lines.get(1);
        assertThat(realHistory).isEqualTo(operation);
    }

    @Test
    public void given_a_deposit_should_print_a_line() {
        account.makeDeposit(new Amount(BigDecimal.valueOf(300.0)));
        StringPrinter printer = new StringPrinter();

        account.printHistory(printer);
        String history = printer.showOutPut();

        String expectedResumeOfOperations = createALineForADeposit();
        assertThat(history).isEqualTo(expectedResumeOfOperations);
    }

    private String createALineForADeposit() {
        LocalDate actualDate = LocalDate.now();
        HistoryDate date = new HistoryDate.HistoryDateBuilder().setDay(new Day(actualDate.getDayOfMonth())).setMonth(new Month(actualDate.getMonthValue())).setYear(new Year(actualDate.getYear())).createHistoryDate();
        return "\t " + date.giveDate() + "\t **** DEPOSIT\t300.0\t\t **** 300.0\n";
    }
}
