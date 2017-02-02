package com.youtube.sorcjc.sga_mobile.ui.fragment.report;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.AsistenciaCurso;
import com.youtube.sorcjc.sga_mobile.domain.User;
import com.youtube.sorcjc.sga_mobile.io.SgaApiAdapter;
import com.youtube.sorcjc.sga_mobile.io.response.AsistenciasResponse;
import com.youtube.sorcjc.sga_mobile.ui.Global;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarFragment extends Fragment implements Callback<AsistenciasResponse> {

    private BarChart barChart;

    public BarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar, container, false);

        barChart = (BarChart) view.findViewById(R.id.barChart);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User usuario = Global.getFromSharedPreferences(getActivity(),"user_login");
        Call<AsistenciasResponse> call = SgaApiAdapter.getApiService().getAsistenciasResponse(usuario.getLogin());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AsistenciasResponse> call, Response<AsistenciasResponse> response) {
        if (response.isSuccessful()) {
            AsistenciasResponse asistenciasResponse = response.body();
            createBarChart(asistenciasResponse);
        } else {
            Toast.makeText(getContext(), "Error al obtener la data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<AsistenciasResponse> call, Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void createBarChart(AsistenciasResponse asistenciasResponse) {
        List<BarEntry> entries = new ArrayList<>();

        ArrayList<AsistenciaCurso> incidentsByProjectArray = asistenciasResponse.getCursosAsistencias();
        int i = 0;
        String[] labels = new String[incidentsByProjectArray.size()];
        for (AsistenciaCurso incidentsByProject: incidentsByProjectArray) {
            entries.add(new BarEntry(i, incidentsByProject.getAsistencias(), incidentsByProject.getNombre()));
            labels[i] = incidentsByProject.getNombre().substring(0,3);
            ++i;
        }

        BarDataSet set = new BarDataSet(entries, "Asistencias por Curso");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width

        set.setColors(new int[] { R.color.colorRed, R.color.colorBlue, R.color.colorGreen }, getContext());

        Description description = new Description();
        description.setTextColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        description.setText("Cantidad de asistencias");
        barChart.setDescription(description);


        barChart.getXAxis().setValueFormatter(new LabelFormatter(labels));

        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh
    }

    public class LabelFormatter implements IAxisValueFormatter {
        private final String[] mLabels;


        LabelFormatter(String[] labels) {
            mLabels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            final int fixedValue = (int) value;

            if (Math.abs(fixedValue-value) <= 0.3) {
                return mLabels[fixedValue];
            }

            return "";
        }
    }

}
