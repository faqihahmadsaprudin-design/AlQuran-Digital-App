package com.example.quranapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.DetailActivity;
import com.example.quranapp.R;
import com.example.quranapp.model.Surah;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {

    Context context;
    List<Surah> list;

    public SurahAdapter(Context context, List<Surah> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, arti, jumlah;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.tvName);
            arti = v.findViewById(R.id.tvArti);
            jumlah = v.findViewById(R.id.tvJumlah);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Surah s = list.get(position);

        holder.name.setText(s.getName());
        holder.arti.setText(s.getEnglishName());
        holder.jumlah.setText("Ayat: " + s.getNumberOfAyahs());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("id", s.getNumber());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}