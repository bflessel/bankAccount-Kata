package fr.lacombedulionvert.kata.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Histories {
    private List<HistoryLine> historyLines;


    Histories() {
        this.historyLines = new ArrayList<>();
    }

    void add(HistoryLine historyLine) {
        historyLines.add(historyLine);
    }

    List<HistoryLine> giveHistoryList() {
        return Collections.unmodifiableList(historyLines);
    }
}
