package com.youtube.sorcjc.sga_mobile.io.response;

import com.google.gson.annotations.SerializedName;
import com.youtube.sorcjc.sga_mobile.domain.CycleNote;
import com.youtube.sorcjc.sga_mobile.domain.User;

import java.util.ArrayList;

/**
 * Created by Juarez on 08/01/2017.
 */

public class LoginResponse {
    @SerializedName("exito")
    private Boolean exito;

    @SerializedName("mensaje")
    private String mensaje;

    @SerializedName("usuario")
    private User usuario;


    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Boolean isExito(){
        return exito;
    }

    public String getRespuesta(){
        return mensaje;
    }
}
