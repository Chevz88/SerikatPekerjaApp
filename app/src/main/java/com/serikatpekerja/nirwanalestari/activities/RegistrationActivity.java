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
import com.serikatpekerja.nirwanalestari.models.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtNama, edtKTP, edtNIK, edtPassword;
    Spinner spinnerDepartemen;
    Button btnDaftar, btnBack;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Binding komponen UI
        edtNama = findViewById(R.id.edtNama);
        edtKTP = findViewById(R.id.edtKTP);
        edtNIK = findViewById(R.id.edtNIK);
        edtPassword = findViewById(R.id.edtPassword);
        spinnerDepartemen = findViewById(R.id.spinnerDepartemen);
        btnDaftar = findViewById(R.id.btnDaftar);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        // Isi spinner dari string-array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this, R.array.departemen_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartemen.setAdapter(adapter);

        // Aksi Daftar
        btnDaftar.setOnClickListener(v -> {
            String nama = edtNama.getText().toString().trim();
            String ktp = edtKTP.getText().toString().trim();
            String nik = edtNIK.getText().toString().trim();
            String departemen = spinnerDepartemen.getSelectedItem().toString();
            String password = edtPassword.getText().toString().trim();

            if (nama.isEmpty() || ktp.isEmpty() || nik.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(nama, ktp, nik, departemen, password);
                long result = dbHelper.insertUser(user);
                if (result > 0) {
                    Toast.makeText(this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                    finish(); // kembali ke login atau sebelumnya
                } else {
                    Toast.makeText(this, "Gagal mendaftar. NIK sudah digunakan?", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
