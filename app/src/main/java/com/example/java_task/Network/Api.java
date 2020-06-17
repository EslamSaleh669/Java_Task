package com.example.java_task.Network;


import com.example.java_task.Model.Cars;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("api/v1/cars")
    Call<Cars> getCars(@Query("page") int pageNum);

}
