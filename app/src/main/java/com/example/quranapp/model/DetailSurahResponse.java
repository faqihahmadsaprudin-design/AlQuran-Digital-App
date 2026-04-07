package com.example.quranapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DetailSurahResponse {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @SerializedName("ayahs")
        private List<Ayat> ayahs;

        public List<Ayat> getAyahs() {
            return ayahs;
        }

        public void setAyahs(List<Ayat> ayahs) {
            this.ayahs = ayahs;
        }
    }
}