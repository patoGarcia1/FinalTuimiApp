package com.example.pato.finaltuimiapp.data.remote;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.pato.finaltuimiapp.data.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pato on 15/7/2018.
 */


public class RetrofitClient {

    private static APIService APIServ;
    private static Context context;


    public static APIService getClient() {
        if (APIServ == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    //.baseUrl("http://192.168.0.8/")
                    .baseUrl("http://localhost:3000/user/")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            APIServ = retrofit.create(APIService.class);
        }
        return APIServ;
    }

    public static void getUser(final OnSuccessCallback callback) {
        getClient().getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.execute(response.body());
                Toast.makeText(context, "Atroden", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Juira", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 2000);}
        });


    }

}