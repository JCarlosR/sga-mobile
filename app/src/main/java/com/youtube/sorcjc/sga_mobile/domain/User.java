package com.youtube.sorcjc.sga_mobile.domain;

import java.io.Serializable;

/**
 * Created by Juarez on 08/01/2017.
 */

public class User implements Serializable {
    private String codigotrabajador;
    private String login;
    private String per_codigo;
    private String per_paterno;
    private String per_materno;
    private String per_nombres;
    private String idEmpresa;
    private String idArea;
    private String idEstructura;
    private String estr_descripcion;
    private String idsede;
    private String rol;
    private String per_dni;
    private String per_celular;
    private String per_email;
    private String per_cargo;

    public User() {
        this.setCodigotrabajador("");
        this.setLogin("");
        this.setPer_codigo("");
        this.setPer_paterno("");
        this.setPer_materno("");
        this.setPer_nombres("");
        this.setIdEmpresa("");
        this.setIdArea("");
        this.setIdEstructura("");
        this.setEstr_descripcion("");
        this.setIdsede("");
        this.setRol("");
        this.setPer_dni("");
        this.setPer_celular("");
        this.setPer_email("");
        this.setPer_cargo("");
    }

    public User(String codigotrabajador, String login, String per_codigo, String per_paterno, String per_materno, String per_nombres, String idEmpresa, String idArea, String idEstructura, String estr_descripcion, String idsede, String rol, String per_dni, String per_celular, String per_email, String per_cargo) {
        this.setCodigotrabajador(codigotrabajador);
        this.setLogin(login);
        this.setPer_codigo(per_codigo);
        this.setPer_paterno(per_paterno);
        this.setPer_materno(per_materno);
        this.setPer_nombres(per_nombres);
        this.setIdEmpresa(idEmpresa);
        this.setIdArea(idArea);
        this.setIdEstructura(idEstructura);
        this.setEstr_descripcion(estr_descripcion);
        this.setIdsede(idsede);
        this.setRol(rol);
        this.setPer_dni(per_dni);
        this.setPer_celular(per_celular);
        this.setPer_email(per_email);
        this.setPer_cargo(per_cargo);
    }


    public String getCodigotrabajador() {
        return codigotrabajador;
    }

    public void setCodigotrabajador(String codigotrabajador) {
        this.codigotrabajador = codigotrabajador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPer_codigo() {
        return per_codigo;
    }

    public void setPer_codigo(String per_codigo) {
        this.per_codigo = per_codigo;
    }

    public String getPer_paterno() {
        return per_paterno;
    }

    public void setPer_paterno(String per_paterno) {
        this.per_paterno = per_paterno;
    }

    public String getPer_materno() {
        return per_materno;
    }

    public void setPer_materno(String per_materno) {
        this.per_materno = per_materno;
    }

    public String getPer_nombres() {
        return per_nombres;
    }

    public void setPer_nombres(String per_nombres) {
        this.per_nombres = per_nombres;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(String idEstructura) {
        this.idEstructura = idEstructura;
    }

    public String getEstr_descripcion() {
        return estr_descripcion;
    }

    public void setEstr_descripcion(String estr_descripcion) {
        this.estr_descripcion = estr_descripcion;
    }

    public String getIdsede() {
        return idsede;
    }

    public void setIdsede(String idsede) {
        this.idsede = idsede;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPer_dni() {
        return per_dni;
    }

    public void setPer_dni(String per_dni) {
        this.per_dni = per_dni;
    }

    public String getPer_celular() {
        return per_celular;
    }

    public void setPer_celular(String per_celular) {
        this.per_celular = per_celular;
    }

    public String getPer_email() {
        return per_email;
    }

    public void setPer_email(String per_email) {
        this.per_email = per_email;
    }

    public String getPer_cargo() {
        return per_cargo;
    }

    public void setPer_cargo(String per_cargo) {
        this.per_cargo = per_cargo;
    }
}
