package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

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
    public void after_multiple_deposits_on_an_empty_should_give_the_amount_of_the_sum_of_the_deposits(Double amount) {
        Account account = new Account();
        Amount newAmount = new Amount(amount);
        account.deposit(newAmount);
        account.deposit(newAmount);
        account.deposit(newAmount);
        Amount totalSavings = account.giveActualBalance();
        Amount realAmount = new Amount(amount * 3);
        Assertions.assertThat(realAmount).isEqualTo(totalSavings);
    }


}
