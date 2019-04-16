package com.khilman.www.aplikasiportalberita.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rizal Hilman on 12/02/18.
 * www.khilman.com
 */

public class InitRetrofit {
    // URL Server API
    public static String API_URL = "https://monitoringaki.000webhostapp.com/sim900/";

    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }
}