package com.serikatpekerja.nirwanalestari.network;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycbyB6UBbh3LW7QGltFAF3XJGA5YnZefUv38gQ7CG5CQ/"; // ganti sesuai endpoint milikmu
    private static Retrofit retrofit = null;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        }
        return retrofit.create(ApiService.class);
    }
}
