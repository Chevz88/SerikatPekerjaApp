package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.MainActivity;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText edtNIK, edtPassword;
    Button btnLogin, btnBack;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtNIK = findViewById(R.id.edtNIK);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String nik = edtNIK.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (dbHelper.checkUser(nik, password)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Login gagal. Periksa NIK dan password!", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
