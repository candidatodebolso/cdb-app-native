package br.com.candidatodebolso.app.fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.candidatodebolso.app.R;
import br.com.candidatodebolso.app.customchart.ConfigureChart;
import br.com.candidatodebolso.app.customchart.EntrySetChart;
import br.com.candidatodebolso.app.customchart.LineDataSetCustomChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollIntentionFragment extends Fragment {


    public PollIntentionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poll_intention, container, false);

        LineChart ibopeChart = view.findViewById(R.id.ibopeChart);

        EntrySetChart haddadIbope = new EntrySetChart(Arrays.asList(37f, 40f, 40f, 37f, 38f, 42f, 41f, 41f, 37f));

        EntrySetChart bolsonaroIbope = new EntrySetChart(Arrays.asList(36f, 36f, 40f, 43f, 42f, 42f, 43f, 45f, 52f));

        LineDataSetCustomChart setComp1Ibope = new LineDataSetCustomChart(haddadIbope.getEntries(), "Haddad", Color.parseColor("#cc0c0c"));
        LineDataSetCustomChart setComp2Ibope = new LineDataSetCustomChart(bolsonaroIbope.getEntries(), "Bolsonaro", Color.parseColor("#207f18"));

        List<ILineDataSet> dataSetsIbope = new ArrayList<>();
        dataSetsIbope.add(setComp1Ibope);
        dataSetsIbope.add(setComp2Ibope);

        LineData dataIbope = new LineData(dataSetsIbope);

        List<String> quartersIbope = Arrays.asList("22/ago", "10/set", "14/set", "20/set", "28/set", "2/out", "4/out", "6/out", "10/out");

        ConfigureChart.configure(ibopeChart, dataIbope, quartersIbope, "IBOPE", view);
        ibopeChart.invalidate();

        LineChart datafolha = view.findViewById(R.id.dataFolhaChart);

        EntrySetChart haddadDataFolha = new EntrySetChart(Arrays.asList(29f, 39f, 40f, 41f, 45f, 42f, 43f, 43f, 36f));

        EntrySetChart bolsonaroDataFolha = new EntrySetChart(Arrays.asList(38f, 38f, 41f, 41f, 39f, 44f, 44f, 45f, 49f));

        LineDataSetCustomChart setComp1Datafolha = new LineDataSetCustomChart(haddadDataFolha.getEntries(), "Haddad", Color.parseColor("#cc0c0c"));
        LineDataSetCustomChart setComp2Datafolha = new LineDataSetCustomChart(bolsonaroDataFolha.getEntries(), "Bolsonaro", Color.parseColor("#207f18"));

        List<ILineDataSet> dataSetsDataFolha = new ArrayList<>();
        dataSetsDataFolha.add(setComp1Datafolha);
        dataSetsDataFolha.add(setComp2Datafolha);

        LineData dataDatafolha = new LineData(dataSetsDataFolha);

        List<String> quartersDatafolha = Arrays.asList("4/set", "11/set", "18/set", "24/set", "26/set", "1/out", "3/out", "6/out", "15/out");

        ConfigureChart.configure(datafolha, dataDatafolha, quartersDatafolha, "IBOPE", view);
        datafolha.invalidate();

        return view;
    }

}
