package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.Laporan;

public class PreviewActivity extends AppCompatActivity {

    TextView txtNama, txtNIK, txtDepartemen, txtIsiAduan;
    Button btnKonfirmasi, btnEdit;
    DatabaseHelper dbHelper;

    String nama, nik, departemen, isiAduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        txtNama = findViewById(R.id.txtNama);
        txtNIK = findViewById(R.id.txtNIK);
        txtDepartemen = findViewById(R.id.txtDepartemen);
        txtIsiAduan = findViewById(R.id.txtIsiAduan);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);
        btnEdit = findViewById(R.id.btnEdit);

        dbHelper = new DatabaseHelper(this);

        // Ambil data dari Intent
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        nik = intent.getStringExtra("nik");
        departemen = intent.getStringExtra("departemen");
        isiAduan = intent.getStringExtra("isi");

        // Tampilkan di TextView
        txtNama.setText("Nama: " + nama);
        txtNIK.setText("NIK: " + nik);
        txtDepartemen.setText("Departemen: " + departemen);
        txtIsiAduan.setText("Isi Aduan: " + isiAduan);

        btnKonfirmasi.setOnClickListener(v -> {
            Laporan laporan = new Laporan(nama, nik, departemen, isiAduan);
            long result = dbHelper.insertLaporan(laporan);
            if (result > 0) {
                Toast.makeText(this, "Laporan berhasil disimpan!", Toast.LENGTH_SHORT).show();
                finish(); // atau arahkan ke halaman History
            } else {
                Toast.makeText(this, "Gagal menyimpan laporan!", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(v -> {
            finish(); // kembali ke form sebelumnya
        });
    }
}
