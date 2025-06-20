package com.serikatpekerja.nirwanalestari.activities;

import android.app.AlertDialog; import android.content.DialogInterface; import android.content.Intent; import android.os.Bundle; import android.widget.Button; import android.widget.EditText; import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R; import com.serikatpekerja.nirwanalestari.database.DatabaseHelper; import com.serikatpekerja.nirwanalestari.MainActivity;

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

        if (nik.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "NIK dan Password harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isValid = dbHelper.checkUser(nik, password);
        if (isValid) {
            new AlertDialog.Builder(this)
                    .setTitle("Login Berhasil")
                    .setMessage("Selamat datang!")
                    .setPositiveButton("Lanjut", (dialog, which) -> {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    })
                    .show();
        } else {
            Toast.makeText(this, "NIK atau Password salah", Toast.LENGTH_SHORT).show();
        }
    });

    btnDaftar.setOnClickListener(v -> {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    });
}

}

