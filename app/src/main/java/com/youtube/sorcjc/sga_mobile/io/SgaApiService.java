package com.youtube.sorcjc.sga_mobile.io;

import com.youtube.sorcjc.sga_mobile.io.response.AsistenciasResponse;
import com.youtube.sorcjc.sga_mobile.io.response.LoginResponse;
import com.youtube.sorcjc.sga_mobile.io.response.NotesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SgaApiService {

    // /sistemaacademicov50/modules/mantenedores/promedioscurso.php?codigo=523300310
    @GET("/sistemaacademicov50/modules/mantenedores/promedioscurso.php")
    Call<NotesResponse> getNotesResponse(@Query("codigo") String code);

    @GET("/sistemaacademicov50/modules/mantenedores/loginmobile.php")
    Call<LoginResponse> getLoginResponse(@Query("user") String user, @Query("pass") String pass, @Query("sede") String sede,@Query("token") String token);

    @GET("/sistemaacademicov50/modules/mantenedores/asistencias.php")
    Call<AsistenciasResponse> getAsistenciasResponse(@Query("codigo") String code);

}
