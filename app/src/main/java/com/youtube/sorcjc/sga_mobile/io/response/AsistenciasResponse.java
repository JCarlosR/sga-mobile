package com.youtube.sorcjc.sga_mobile.io.response;

import com.youtube.sorcjc.sga_mobile.domain.AsistenciaCurso;

import java.util.ArrayList;

/**
 * Created by Juarez on 02/02/2017.
 */

public class AsistenciasResponse {

    private ArrayList<AsistenciaCurso> cursosAsistencias;


    public ArrayList<AsistenciaCurso> getCursosAsistencias() {
        return cursosAsistencias;
    }

    public void setCursosAsistencias(ArrayList<AsistenciaCurso> cursosAsistencias) {
        this.cursosAsistencias = cursosAsistencias;
    }
}
