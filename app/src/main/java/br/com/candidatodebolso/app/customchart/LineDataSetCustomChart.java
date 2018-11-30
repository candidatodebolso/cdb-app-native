package br.com.candidatodebolso.app.customchart;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

public class LineDataSetCustomChart extends LineDataSet {
    public LineDataSetCustomChart(List<Entry> yVals, String label) {
        super(yVals, label);
    }

    public LineDataSetCustomChart(List<Entry> uValues, String label, int color) {
        this(uValues, label);
        setAxisDependency(YAxis.AxisDependency.LEFT);
        setColor(color);
        setCircleColor(color);
        setCircleRadius(5f);
        setLineWidth(2f);
        setValueTextSize(10f);
    }
}
