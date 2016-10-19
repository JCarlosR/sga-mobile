package com.youtube.sorcjc.sga_mobile.io.response;

import com.google.gson.annotations.SerializedName;
import com.youtube.sorcjc.sga_mobile.domain.CycleNote;

import java.util.ArrayList;

public class NotesResponse {

    @SerializedName("notas")
    private
    ArrayList<CycleNote> notes;

    public ArrayList<CycleNote> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<CycleNote> notes) {
        this.notes = notes;
    }
}
