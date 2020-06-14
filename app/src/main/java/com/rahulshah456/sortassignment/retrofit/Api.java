package com.rahulshah456.sortassignment.retrofit;

import com.rahulshah456.sortassignment.models.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.rahulshah456.sortassignment.utils.Constant.BASE_URL;

public interface Api {

    @GET("dev/profile/listAll")
    Call<Response> getAllUsers();

}
