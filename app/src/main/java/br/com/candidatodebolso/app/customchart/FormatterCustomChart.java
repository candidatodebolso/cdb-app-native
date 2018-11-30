package br.com.candidatodebolso.app.customchart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

public class FormatterCustomChart implements IAxisValueFormatter {

    private List<String> quarters;

    public FormatterCustomChart(List<String> quarters) {
        this.quarters = quarters;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return quarters.get((int) value);
    }
}
