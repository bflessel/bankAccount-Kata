package fr.lacombedulionvert.kata.domain.history;

import java.util.List;

public class StatementPrinter implements Printer {
    @Override
    public void printHistory(List<String> history) {
        for (String operation : history) {
            System.out.println(operation);
        }
    }
}
