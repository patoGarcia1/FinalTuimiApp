package com.example.pato.finaltuimiapp;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pato on 12/7/2018.
 */

public class Usuario implements Serializable {


    private int idUsuario;
    private String nombre;
    private String password;
    private String email;
    private int puntos;
    private int cantPost;
    private ArrayList<Post> posts = new ArrayList<Post>();
  //  private static int contadorIdUsuarios;



    public Usuario(String nombre, String password){//, String email) {
    //    contadorIdUsuarios++;
    //    this.idUsuario = contadorIdUsuarios;
        this.nombre = nombre;
        this.password = password;
    //    this.email = email;

    }

    public Usuario(EditText usuario, EditText contraseña) {
    }

    /*public static void setContadorIdUsuarios(int contadorIdUsuarios) {
        Usuario.contadorIdUsuarios = contadorIdUsuarios;
    }

    public static int getContadorIdUsuarios() {
        return contadorIdUsuarios;
    }*/

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setCantPost(int cantPost) {
        this.cantPost = cantPost;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getCantPost() {
        return cantPost;
    }
}
