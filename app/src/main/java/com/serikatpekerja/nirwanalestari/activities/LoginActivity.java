package com.serikatpekerja.nirwanalestari.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.MainActivity;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText edtNIK, edtPassword;
    Button btnLogin;
    TextView txtDaftar;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNIK = findViewById(R.id.edtNIK);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtDaftar = findViewById(R.id.txtDaftar);

        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String nik = edtNIK.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (dbHelper.checkUser(nik, password)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user_nik", nik);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Login gagal. Periksa NIK dan password!", Toast.LENGTH_SHORT).show();
            }
        });

        txtDaftar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah kamu yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya, saya yakin", (dialog, which) -> {
                // Arahkan kembali ke splash screen
                Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            })
            .setNegativeButton("Tidak", null)
            .show();
    }
}
