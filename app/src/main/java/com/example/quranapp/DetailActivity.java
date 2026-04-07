package com.example.quranapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quranapp.api.*;
import com.example.quranapp.model.*;

import java.util.List;

import retrofit2.*;

public class DetailActivity extends AppCompatActivity {

    TextView tvAyat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvAyat = findViewById(R.id.tvAyat);

        // Ambil ID Surah dari Intent (default 1)
        int id = getIntent().getIntExtra("id", 1);

        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        api.getDetailSurah(id).enqueue(new Callback<DetailSurahResponse>() {
            @Override
            public void onResponse(Call<DetailSurahResponse> call, Response<DetailSurahResponse> response) {
                // Pastikan data ada sebelum diproses
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {

                    StringBuilder sb = new StringBuilder();
                    List<Ayat> listAyat = response.body().getData().getAyahs();

                    for (Ayat a : listAyat) {
                        // Gabungkan teks ayat
                        sb.append(a.getText()).append("\n\n");
                    }

                    tvAyat.setText(sb.toString());
                } else {
                    Toast.makeText(DetailActivity.this, "Gagal memuat ayat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailSurahResponse> call, Throwable t) {
                Log.e("API_ERROR", "Detail Error: " + t.getMessage());
                Toast.makeText(DetailActivity.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}