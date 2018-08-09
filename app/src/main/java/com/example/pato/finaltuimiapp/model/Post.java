package com.example.pato.finaltuimiapp.model;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by Pato on 12/7/2018.
 */
@IgnoreExtraProperties
public class Post {

    public String descripcion;
    public String imagen;
    public String coordenadas;


    public Post() {
    }

    public Post(String descripcion, String imagen, String coordenadas) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.coordenadas = coordenadas;
    }
}