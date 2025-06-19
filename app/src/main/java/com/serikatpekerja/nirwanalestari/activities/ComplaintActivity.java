package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.Laporan;

public class ComplaintActivity extends AppCompatActivity {

    EditText edtNama, edtNIK, edtDepartemen, edtIsiAduan;
    Button btnKirim, btnKembali;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        edtNama = findViewById(R.id.edtNama);
        edtNIK = findViewById(R.id.edtNIK);
        edtDepartemen = findViewById(R.id.edtDepartemen);
        edtIsiAduan = findViewById(R.id.edtIsiAduan);
        btnKirim = findViewById(R.id.btnKirim);
        btnKembali = findViewById(R.id.btnKembali);

        dbHelper = new DatabaseHelper(this);

        btnKirim.setOnClickListener(v -> {
            String nama = edtNama.getText().toString().trim();
            String nik = edtNIK.getText().toString().trim();
            String departemen = edtDepartemen.getText().toString().trim();
            String isi = edtIsiAduan.getText().toString().trim();

            if (nama.isEmpty() || nik.isEmpty() || departemen.isEmpty() || isi.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            Laporan laporan = new Laporan(nama, nik, departemen, isi);
            long result = dbHelper.insertLaporan(laporan);

            if (result > 0) {
                Toast.makeText(this, "Aduan berhasil disimpan (offline)", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Gagal menyimpan aduan", Toast.LENGTH_SHORT).show();
            }
        });

        btnKembali.setOnClickListener(v -> finish());
    }
}
