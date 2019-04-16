package com.khilman.www.aplikasiportalberita.services;


import com.khilman.www.aplikasiportalberita.models.ListLocationModel;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {
    @GET("maps.php")
    Call<ListLocationModel> getAllLocation();
}
