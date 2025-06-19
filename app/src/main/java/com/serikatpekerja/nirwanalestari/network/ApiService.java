package com.serikatpekerja.nirwanalestari.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("exec") // hanya path-nya saja karena baseURL akan ditentukan di RetrofitClient
    Call<Void> kirimAduan(
        @Field("nama") String nama,
        @Field("nik") String nik,
        @Field("departemen") String departemen,
        @Field("isi") String isi
    );
}
