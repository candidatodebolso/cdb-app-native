package br.com.candidatodebolso.app.customchart;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntrySetChart {

    private List<Entry> entries;

    public EntrySetChart(List<Float> values) {
        entries = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            entries.add(new Entry((float) i, values.get(i)));
        }
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
