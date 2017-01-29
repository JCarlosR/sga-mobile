package com.youtube.sorcjc.sga_mobile.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CourseNote implements Serializable {

    @SerializedName("nombre")
    private String name;

    @SerializedName("docente")
    private String docente;

    @SerializedName("nota1")
    private String note1;
    @SerializedName("nota2")
    private String note2;
    @SerializedName("nota3")
    private String note3;

    @SerializedName("promediofinal")
    private String average;

    @SerializedName("matricula")
    private String enrollment;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("creditos")
    private int creditos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}
