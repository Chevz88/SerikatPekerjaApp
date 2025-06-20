package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.R;

public class EdukasiDetailActivity extends AppCompatActivity {

    TextView txtJudul, txtIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi_detail);

        txtJudul = findViewById(R.id.txtJudul);
        txtIsi = findViewById(R.id.txtIsi);

        String judul = getIntent().getStringExtra("judul");
        String isi = getIntent().getStringExtra("isi");

        txtJudul.setText(judul);
        txtIsi.setText(isi);
    }
}
