package com.rahulshah456.sortassignment.retrofit;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.rahulshah456.sortassignment.utils.Constant.BASE_URL;

public class Service {

    private static Api service;
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build();

    public static Api getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(client)
                    .build();
            service = retrofit.create(Api.class);
        }
        return service;
    }

}
