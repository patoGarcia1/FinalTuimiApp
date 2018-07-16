package com.example.pato.finaltuimiapp.data.model;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pato on 12/7/2018.
 */

public class User {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("email")
    @Expose
    private String email;

    public User(EditText user, EditText pass) {
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}