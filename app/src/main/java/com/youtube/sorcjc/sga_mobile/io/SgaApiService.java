package com.youtube.sorcjc.sga_mobile.io;

import com.youtube.sorcjc.sga_mobile.io.response.NotesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SgaApiService {

    // /sistemaacademicov50/modules/mantenedores/promedioscurso.php?codigo=523300310
    @GET("/sistemaacademicov50/modules/mantenedores/promedioscurso.php")
    Call<NotesResponse> getNotesResponse(@Query("codigo") String code);

}
