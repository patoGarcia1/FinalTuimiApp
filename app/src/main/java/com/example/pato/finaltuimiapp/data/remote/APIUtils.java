package com.example.pato.finaltuimiapp.data.remote;

/**
 * Created by Pato on 15/7/2018.
 */

public class APIUtils {

    private APIUtils(){}

    public static final String BASE_URL = "https://my-json-server.typicode.com/patoGarcia1/FinalTuimiApp/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
