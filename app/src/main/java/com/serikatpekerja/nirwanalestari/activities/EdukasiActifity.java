package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;

public class EdukasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);
    }

    // Saat user klik topik "Hak-Hak Pekerja"
    public void openHakPekerja(View view) {
        Intent intent = new Intent(this, EdukasiDetailActivity.class);
        intent.putExtra("judul", "Hak-Hak Pekerja");
        intent.putExtra("isi", "Setiap pekerja memiliki hak atas upah yang layak, perlindungan kerja, jaminan sosial, dan hak berserikat sesuai UU No. 13 Tahun 2003 tentang Ketenagakerjaan.");
        startActivity(intent);
    }

    // Saat user klik topik "Apa Itu Serikat Pekerja"
    public void openSerikatPekerja(View view) {
        Intent intent = new Intent(this, EdukasiDetailActivity.class);
        intent.putExtra("judul", "Apa Itu Serikat Pekerja?");
        intent.putExtra("isi", "Serikat pekerja adalah organisasi yang dibentuk dari, oleh, dan untuk pekerja guna memperjuangkan hak dan kepentingan mereka, termasuk negosiasi upah, kondisi kerja, dan keadilan.");
        startActivity(intent);
    }
}
