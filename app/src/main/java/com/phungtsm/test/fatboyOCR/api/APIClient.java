package com.phungtsm.test.fatboyOCR.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phungtsm.test.fatboyOCR.Currency;
import com.phungtsm.test.fatboyOCR.Post;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class APIClient {
    // Link API: https://ocr.mobile-id.vn/api/v1/vision/vnm/login
    public static final String BASE_URL = "https://ocr.mobile-id.vn/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
