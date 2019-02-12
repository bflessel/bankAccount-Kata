package fr.lacombedulionvert.kata.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    @Test
    public void after_multiples_deposits_and_withdrawals_should_see_the_history_of_all_operation() {
        Account account = new Account();
        account.deposit(new Amount(300.0));
        account.withdrawal(new Amount(200.0));
        account.withdrawal(new Amount(0.0));
        StringPrinter printer = new StringPrinter();
        account.printHistory(printer);
        String history = printer.showOutPut();
        String expectedResumeOfOperations = createExpectedString();
        assertThat(expectedResumeOfOperations).isEqualTo(history);
    }

    private String createExpectedString() {
        LocalDate actualDate = LocalDate.now();
        return "\t " + actualDate + "\t **** DEPOSIT\t300.0\t\t **** 300.0\n"
                + "\t " + actualDate + "\t **** WITHDRAWAL\t-200.0\t\t **** 100.0\n"
                + "\t " + actualDate + "\t **** NAN\t0.0\t\t **** 100.0\n";
    }

}
