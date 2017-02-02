package com.youtube.sorcjc.sga_mobile.domain;

/**
 * Created by Juarez on 02/02/2017.
 */

public class AsistenciaCurso {
    private String nombre;
    private String ciclo;
    private String matricula;
    private int total;
    private int faltas;
    private int asistencias;

    public AsistenciaCurso() {
        this.setNombre("");
        this.setCiclo("");
        this.setMatricula("");
        this.setTotal(0);
        this.setFaltas(0);
        this.setAsistencias(0);
    }

    public AsistenciaCurso(String nombre, String ciclo, String matricula, int total, int faltas, int asistencias) {
        this.setNombre(nombre);
        this.setCiclo(ciclo);
        this.setMatricula(matricula);
        this.setTotal(total);
        this.setFaltas(faltas);
        this.setAsistencias(asistencias);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }
}
