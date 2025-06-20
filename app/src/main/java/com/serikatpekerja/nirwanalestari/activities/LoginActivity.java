package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.DashboardActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtNIK, edtPassword;
    Button btnLogin, btnBack;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view
        edtNIK = findViewById(R.id.edtNIK);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String nik = edtNIK.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (nik.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "NIK dan password harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.checkUser(nik, password)) {
                // Ambil nama user dari database
                String namaUser = dbHelper.getUserName(nik); // Pastikan kamu punya fungsi getUserName(nik)

                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("user_name", namaUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login gagal. Periksa NIK dan password!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
