package com.mvvm.softxperttestapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {
    @GET("cars")
    Call<CarResponse> getUserList(@Query("page") int page);
}
