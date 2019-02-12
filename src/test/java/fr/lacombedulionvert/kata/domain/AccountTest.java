package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class AccountTest {

    @Property
    public void after_making_a_deposit_on_an_empty_account_should_give_the_amount_of_the_deposit(Double amount) {
        Account account = new Account();
        account.deposit(new Amount(amount));
        Amount totalSavings = account.giveActualBalance();
        Assertions.assertThat(new Amount(amount)).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_deposits_on_an_empty_should_give_the_amount_of_the_sum_of_the_deposits(Double amount, Double otherAmount, Double thirdAmount) {
        Account account = new Account();
        account.deposit(new Amount(amount));
        account.deposit(new Amount(otherAmount));
        account.deposit(new Amount(thirdAmount));
        Amount totalSavings = account.giveActualBalance();
        Amount realAmount = new Amount(amount + otherAmount + thirdAmount);
        Assertions.assertThat(realAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_making_a_withdrawal_on_empty_account_should_give_the_amount_of_the_deposit(Double amount) {
        Account account = new Account();
        account.withdrawal(new Amount(amount));
        Amount totalSavings = account.giveActualBalance();
        Assertions.assertThat(new Amount(-amount)).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_withdrawals_on_an_empty_should_give_the_amount_of_the_substraction_of_the_withdrawals(Double amount, Double otherAmount, Double thirdAmount) {
        Account account = new Account();
        account.withdrawal(new Amount(amount));
        account.withdrawal(new Amount(otherAmount));
        account.withdrawal(new Amount(thirdAmount));
        Amount totalSavings = account.giveActualBalance();
        Amount realAmount = new Amount(-(amount + otherAmount + thirdAmount));
        Assertions.assertThat(realAmount).isEqualTo(totalSavings);
    }

    @Property
    public void after_multiple_withdrawals_and_deposit_on_an_empty_should_give_the_amount_of_the_sum_of_all(Double amount, Double otherAmount, Double thirdAmount) {
        Account account = new Account();
        account.deposit(new Amount(amount));
        account.withdrawal(new Amount(otherAmount));
        account.deposit(new Amount(thirdAmount));
        Amount totalSavings = account.giveActualBalance();
        Amount realAmount = new Amount(amount - otherAmount + thirdAmount);
        Assertions.assertThat(realAmount).isEqualTo(totalSavings);
    }


    @Test
    public void after_making_a_deposit_on_an_empty_account_should_give_the_history_of_the_deposit() {
        Account account = new Account();
        Amount newAmount = new Amount(100.0);
        account.deposit(newAmount);
        List<HistoryLine> lines = account.showHistoryLines();
        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        HistoryLine historyLine = new HistoryLineBuilder().setDate(date).setNewAmount(newAmount).setDeposit(OperationType.DEPOSIT).createHistoryLine();
        HistoryLine realHistory = lines.get(0);
        Assertions.assertThat(historyLine).isEqualTo(realHistory);
    }


}
