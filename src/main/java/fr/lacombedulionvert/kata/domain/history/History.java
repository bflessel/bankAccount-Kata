package fr.lacombedulionvert.kata.domain.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;

public class History {
    private final List<Operation> operations;

    public History() {
        this.operations = new ArrayList<>();
    }

    public void add(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> giveOperations() {
        return Collections.unmodifiableList(operations);
    }

    public List<String> showStatement() {
        DoubleAdder total = new DoubleAdder();
        List<StatementLine> statementLines = operations.stream().map(Operation::generateStatementLine).collect(Collectors.toList());
        return statementLines.stream().map(line -> line.generateHistory(total)).collect(Collectors.toList());
    }


}
