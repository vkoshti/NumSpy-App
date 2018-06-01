package com.easylife.numspy.numspyapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NumSpyApiService {
    private static final String BASE_URL = "https://numspy.pythonanywhere.com/";
    public static <T> T createStatic(Class<T> tClass) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        return builder
                .build()
                .create(tClass);

    }
}
