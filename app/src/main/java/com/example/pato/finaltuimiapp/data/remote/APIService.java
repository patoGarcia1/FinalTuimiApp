package com.example.pato.finaltuimiapp.data.remote;

import com.example.pato.finaltuimiapp.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Pato on 15/7/2018.
 */

public interface APIService {

    //Registra user
    @POST("user")
    @FormUrlEncoded
    Call<User> saveUser(@Field("user") String user,
                        @Field("pass") String pass,
                        @Field("email") String email);

    //Get de user
    @GET("user")
    Call<User> getUser();
}
