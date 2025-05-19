package com.unl.music.base.controller.dao.dao_models;

import com.unl.music.base.models.RolArtistaEnum;

public class DaoArtista_Banda {
    private Integer id;
    private RolArtistaEnum rol;
    private Integer id_artista;
    private Integer id_banda;
    private String nombreArtista;
    private String nombreBanda;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public RolArtistaEnum getRol() {
        return rol;
    }
    public void setRol(RolArtistaEnum rol) {
        this.rol = rol;
    }
    public Integer getId_artista() {
        return id_artista;
    }
    public void setId_artista(Integer id_artista) {
        this.id_artista = id_artista;
    }
    public Integer getId_banda() {
        return id_banda;
    }
    public void setId_banda(Integer id_banda) {
        this.id_banda = id_banda;
    }
    public String getNombreArtista() {
        return nombreArtista;
    }
    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }
    public String getNombreBanda() {
        return nombreBanda;
    }
    public void setNombreBanda(String nombreBanda) {
        this.nombreBanda = nombreBanda;
    }
}