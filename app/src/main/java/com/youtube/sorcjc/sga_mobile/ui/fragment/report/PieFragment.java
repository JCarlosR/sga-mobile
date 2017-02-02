package com.youtube.sorcjc.sga_mobile.ui.fragment.report;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youtube.sorcjc.sga_mobile.R;

import java.util.ArrayList;

public class PieFragment extends Fragment implements Callback<IncidentsStateResponse> {

    private PieChart pieChart;

    public PieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, container, false);

        pieChart = (PieChart) view.findViewById(R.id.pieChart);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Call<IncidentsStateResponse> call = IncidentApiAdapter.getApiService().getIncidentsState();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<IncidentsStateResponse> call, Response<IncidentsStateResponse> response) {
        if (response.isSuccessful()) {
            IncidentsStateResponse incidentsStateResponse = response.body();
            createPieChart(incidentsStateResponse);
        } else {
            Toast.makeText(getContext(), "Error al obtener la data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<IncidentsStateResponse> call, Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void createPieChart(IncidentsStateResponse incidentsStateResponse) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // Here PUT the quantities, JUAREZ !!
        entries.add(new PieEntry(incidentsStateResponse.getPending(), "Pendiente"));
        entries.add(new PieEntry(incidentsStateResponse.getAssigned(), "Asignado"));
        entries.add(new PieEntry(incidentsStateResponse.getSolved(), "Resuelto"));

        PieDataSet dataSet = new PieDataSet(entries, "Incidencias");
        dataSet.setColors(new int[] { R.color.colorRed, R.color.colorBlue, R.color.colorGreen }, getContext());

        PieData data = new PieData(dataSet);

        pieChart.setData(data);

        Description description = new Description();
        description.setTextColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        description.setTextSize(18f);
        description.setText("REEMPLAZAR POR TÍTULO DEL REPORTE");

        pieChart.setDescription(description);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }
}
