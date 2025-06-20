package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.Laporan;

public class ComplaintActivity extends AppCompatActivity {

    EditText edtNama, edtNIK, edtIsi;
    Spinner spinnerDepartemen;
    Button btnKirim, btnBack;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        // Binding view
        edtNama = findViewById(R.id.edtNama);
        edtNIK = findViewById(R.id.edtNIK);
        edtIsi = findViewById(R.id.edtIsi);
        spinnerDepartemen = findViewById(R.id.spinnerDepartemen);
        btnKirim = findViewById(R.id.btnKirim);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        // Setup spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this, R.array.departemen_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartemen.setAdapter(adapter);

        // Tombol kirim
        btnKirim.setOnClickListener(v -> {
            String nama = edtNama.getText().toString().trim();
            String nik = edtNIK.getText().toString().trim();
            String isi = edtIsi.getText().toString().trim();
            String departemen = spinnerDepartemen.getSelectedItem().toString();

            if (nama.isEmpty() || nik.isEmpty() || isi.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                Laporan laporan = new Laporan(nama, nik, departemen, isi);
                long result = dbHelper.insertLaporan(laporan);
                if (result > 0) {
                    Toast.makeText(this, "Pengaduan berhasil dikirim", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Gagal mengirim pengaduan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
