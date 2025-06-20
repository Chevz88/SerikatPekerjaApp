package com.serikatpekerja.nirwanalestari.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    Button btnLogin, btnDaftar;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNIK = findViewById(R.id.edtNIK);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnDaftar = findViewById(R.id.btnDaftar);

        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String nik = edtNIK.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (dbHelper.checkUser(nik, password)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("user_name", nik);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login gagal. Periksa NIK dan password!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDaftar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi Keluar")
                .setMessage("Apa kamu yakin untuk keluar dari aplikasi?")
                .setPositiveButton("Ya, saya yakin", (dialog, which) -> {
                    finishAffinity();
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
