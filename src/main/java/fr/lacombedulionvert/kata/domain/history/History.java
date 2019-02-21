package fr.lacombedulionvert.kata.domain.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;

public class History {
    private final List<HistoryLine> historyLines;


    public History() {
        this.historyLines = new ArrayList<>();
    }

    public void add(HistoryLine historyLine) {
        historyLines.add(historyLine);
    }

    public List<HistoryLine> giveHistoryList() {
        return Collections.unmodifiableList(historyLines);
    }

    public List<String> showStatement() {
        DoubleAdder total = new DoubleAdder();
        List<StatementLine> statementLines = historyLines.stream().map(HistoryLine::generateStatementLine).collect(Collectors.toList());
        return statementLines.stream().map(line -> line.generateHistory(total)).collect(Collectors.toList());

    }


}
