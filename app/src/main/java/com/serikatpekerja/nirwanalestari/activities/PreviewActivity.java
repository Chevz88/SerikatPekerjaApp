package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;

public class PreviewActivity extends AppCompatActivity {

    TextView tvNama, tvNIK, tvDepartemen, tvIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        tvNama = findViewById(R.id.tvNama);
        tvNIK = findViewById(R.id.tvNIK);
        tvDepartemen = findViewById(R.id.tvDepartemen);
        tvIsi = findViewById(R.id.tvIsiAduan);

        // Ambil data dari intent jika ada
        String nama = getIntent().getStringExtra("nama");
        String nik = getIntent().getStringExtra("nik");
        String departemen = getIntent().getStringExtra("departemen");
        String isi = getIntent().getStringExtra("isi");

        tvNama.setText(nama);
        tvNIK.setText(nik);
        tvDepartemen.setText(departemen);
        tvIsi.setText(isi);
    }
}
