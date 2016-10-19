package com.youtube.sorcjc.sga_mobile.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CycleNote {

    @SerializedName("cursos")
    private ArrayList<CourseNote> courses;
    @SerializedName("ciclo")
    private String cycle;

    public ArrayList<CourseNote> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseNote> courses) {
        this.courses = courses;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}
