package fr.lacombedulionvert.kata.domain;

import fr.lacombedulionvert.kata.domain.history.Printer;

import java.util.List;

public class StringPrinter implements Printer {
    private final StringBuilder builder;

    StringPrinter() {
        this.builder = new StringBuilder();
    }

    String showOutPut() {
        return builder.toString();
    }

    @Override
    public void printHistory(List<String> history) {
        for (String line : history) {
            builder.append(line);
        }
    }
}
