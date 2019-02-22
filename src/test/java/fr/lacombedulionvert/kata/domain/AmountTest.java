package fr.lacombedulionvert.kata.domain;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import fr.lacombedulionvert.kata.domain.exceptions.NegativeOrNullAmountException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(JUnitQuickcheck.class)
public class AmountTest {

    @Test
    public void should_not_be_created_when_given_a_null_amount() {
        Assertions.assertThatThrownBy(() -> new Amount(BigDecimal.ZERO))
                .isInstanceOf(NegativeOrNullAmountException.class);
    }

    @Property
    public void should_not_be_created_when_given_a_negative_amount(@InRange(maxDouble = 0) double amount) {
        Assertions.assertThatThrownBy(() -> new Amount(BigDecimal.valueOf(amount)))
                .isInstanceOf(NegativeOrNullAmountException.class);
    }

    @Property
    public void should_give_the_substraction_of_amounts(@InRange(minInt = 1002, maxInt = 1000000000) int amount, @InRange(minInt = 1, maxInt = 1000) int otherAmount) {
        Amount subAmount = new Amount(BigDecimal.valueOf(otherAmount)).minus(new Amount(BigDecimal.valueOf(amount)));
        Amount expectedAmount = new Amount(BigDecimal.valueOf(amount - otherAmount));
        Assertions.assertThat(subAmount).isEqualTo(expectedAmount);
    }


    @Property
    public void should_give_the_addition_of_amounts(@InRange(minInt = 1, maxInt = Integer.MAX_VALUE / 2) Integer amount, @InRange(minInt = 1, maxInt = (Integer.MAX_VALUE - 1) / 2) Integer otherAmount) {
        Amount addAmount = new Amount(BigDecimal.valueOf(otherAmount)).plus(new Amount(BigDecimal.valueOf(amount)));
        Amount expectedAmount = new Amount(BigDecimal.valueOf(amount + otherAmount));
        Assertions.assertThat(addAmount).isEqualTo(expectedAmount);
    }


}
