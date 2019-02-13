package fr.lacombedulionvert.kata.domain.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;

public class Histories {
    private final List<HistoryLine> historyLines;


    public Histories() {
        this.historyLines = new ArrayList<>();
    }

    public void add(HistoryLine historyLine) {
        historyLines.add(historyLine);
    }

    public List<HistoryLine> giveHistoryList() {
        return Collections.unmodifiableList(historyLines);
    }

    public List<String> showHistory() {
        DoubleAdder total = new DoubleAdder();
        return historyLines.stream().map(line -> line.generateHistory(total)).collect(Collectors.toList());

    }
}
