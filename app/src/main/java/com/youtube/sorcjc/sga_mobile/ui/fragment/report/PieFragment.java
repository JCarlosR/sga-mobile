package com.youtube.sorcjc.sga_mobile.ui.fragment.report;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

public class PieFragment extends Fragment implements Callback<AsistenciasResponse> {

    private PieChart pieChart;
    private Spinner curso;
    private AsistenciasResponse asistenciasResponse;

    public PieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pie, container, false);
        curso = (Spinner) view.findViewById(R.id.spinnerCourse);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        curso.setOnItemSelectedListener(mySpinnerListener);
        return view;
    }

    private AdapterView.OnItemSelectedListener mySpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            createPieChart(asistenciasResponse,i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User usuario = Global.getFromSharedPreferences(getActivity(),"user_login");
        Call<AsistenciasResponse> call = SgaApiAdapter.getApiService().getAsistenciasResponse(usuario.getLogin());
        call.enqueue(this);
    }

    private void poblarSpinnerResponsables(ArrayList<AsistenciaCurso> cursos) {
        List<String> list = new ArrayList<String>();
        for (AsistenciaCurso r : cursos) {
            list.add(r.getNombre());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, list);
        // spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curso.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onResponse(Call<AsistenciasResponse> call, Response<AsistenciasResponse> response) {
        if (response.isSuccessful()) {
            asistenciasResponse = response.body();
            poblarSpinnerResponsables(asistenciasResponse.getCursosAsistencias());
        } else {
            Toast.makeText(getContext(), "Error al obtener la data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<AsistenciasResponse> call, Throwable t) {
        Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    private void createPieChart(AsistenciasResponse asistenciasResponse, Integer index) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // Here PUT the quantities, JUAREZ !!
        Integer total,asistencias,faltas;
        total = asistenciasResponse.getCursosAsistencias().get(index).getTotal();
        asistencias = asistenciasResponse.getCursosAsistencias().get(index).getAsistencias();
        faltas = asistenciasResponse.getCursosAsistencias().get(index).getFaltas();
        entries.add(new PieEntry(total - asistencias-faltas , "Disponibles"));
        if(asistencias>0)
            entries.add(new PieEntry(asistencias, "Asistencias"));
        if(faltas>0)
            entries.add(new PieEntry(faltas, "Faltas"));

        PieDataSet dataSet = new PieDataSet(entries, asistenciasResponse.getCursosAsistencias().get(index).getNombre());
        dataSet.setColors(new int[] { R.color.colorGreen, R.color.colorBlue, R.color.colorRed }, getContext());

        PieData data = new PieData(dataSet);

        pieChart.setData(data);

        Description description = new Description();
        description.setTextColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        description.setTextSize(18f);
        description.setText("REPORTE DE ASISTENCIAS");

        pieChart.setDescription(description);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }
}
