package com.example.pato.finaltuimiapp.model;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by Pato on 12/7/2018.
 */
@IgnoreExtraProperties
public class User {

    public String usuario;
    public String contraseña;
    public String correo;

    // Se necesita el contructor por default para hacer llamadas
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String usuario, String contraseña, String correo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
    }
}