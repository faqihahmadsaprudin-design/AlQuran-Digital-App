package com.example.quranapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.example.quranapp.adapter.SurahAdapter;
import com.example.quranapp.api.*;
import com.example.quranapp.model.*;
import java.util.List;
import retrofit2.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        api.getSurahList().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                // Tambahkan pengecekan null agar tidak Force Close
                if (response.isSuccessful() && response.body() != null) {
                    List<Surah> list = response.body().getData();
                    recyclerView.setAdapter(new SurahAdapter(MainActivity.this, list));
                } else {
                    Toast.makeText(MainActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Cek koneksi internet anda", Toast.LENGTH_SHORT).show();
            }
        });
    }
}