package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.dateManagement.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    @Test
    public void after_multiples_deposits_and_withdrawals_should_see_the_history_of_all_operation() {
        Account account = new Account();
        account.makeDeposit(new Amount(300.0));
        account.makeWithdrawal(new Amount(200.0));
        StringPrinter printer = new StringPrinter();
        account.printHistory(printer);
        String history = printer.showOutPut();
        String expectedResumeOfOperations = createExpectedString();
        assertThat(expectedResumeOfOperations).isEqualTo(history);
    }

    private String createExpectedString() {
        LocalDate localDate = LocalDate.now();
        HistoryDate date = new HistoryDateBuilder().setDay(new Day(localDate.getDayOfMonth())).setMonth(new Month(localDate.getMonthValue())).setYear(new Year(localDate.getYear())).createHistoryDate();
        return "\t " + date.giveDate() + "\t **** DEPOSIT\t300.0\t\t **** 300.0\n"
                + "\t " + date.giveDate() + "\t **** WITHDRAWAL\t-200.0\t\t **** 100.0\n"
                ;
    }

}
