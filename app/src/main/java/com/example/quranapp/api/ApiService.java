package com.example.quranapp.api;

import com.example.quranapp.model.SurahResponse;
import com.example.quranapp.model.DetailSurahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("surah")
    Call<SurahResponse> getSurahList();

    @GET("surah/{id}")
    Call<DetailSurahResponse> getDetailSurah(@Path("id") int id);
}