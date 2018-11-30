package br.com.candidatodebolso.app.customchart;

import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

import br.com.candidatodebolso.app.R;

public class ConfigureChart {

    public static void configure(LineChart lineChart, LineData data, List<String> quarters, String description, View view) {

        lineChart.setData(data);

        lineChart.setBackgroundColor(view.getResources().getColor(R.color.white));
        lineChart.setDrawGridBackground(false);

        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        lineChart.getDescription().setText(description);

        Legend legend = lineChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setForm(Legend.LegendForm.LINE);

        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new FormatterCustomChart(quarters));
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(10f);
    }
}
