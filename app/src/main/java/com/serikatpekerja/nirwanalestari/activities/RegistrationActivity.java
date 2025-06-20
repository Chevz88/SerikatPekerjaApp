package com.serikatpekerja.nirwanalestari.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtNama, edtKTP, edtNIK, edtPassword;
    Spinner edtDepartemen;
    Button btnUploadKTP, btnDaftar, btnBack;
    ImageView imgKTPPreview;

    String base64KTP = "";

    DatabaseHelper dbHelper;
    public static final int PICK_IMAGE_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtNama = findViewById(R.id.edtNama);
        edtKTP = findViewById(R.id.edtKTP);
        edtNIK = findViewById(R.id.edtNIK);
        edtDepartemen = findViewById(R.id.edtDepartemen);
        edtPassword = findViewById(R.id.edtPassword);
        btnUploadKTP = findViewById(R.id.btnUploadKTP);
        imgKTPPreview = findViewById(R.id.imgKTPPreview);
        btnDaftar = findViewById(R.id.btnDaftar);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHelper(this);

        // Set isi spinner departemen
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departemen_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtDepartemen.setAdapter(adapter);

        btnUploadKTP.setOnClickListener(v -> {
            Intent pickImage = new Intent(Intent.ACTION_GET_CONTENT);
            pickImage.setType("image/*");
            startActivityForResult(Intent.createChooser(pickImage, "Pilih Foto KTP"), PICK_IMAGE_REQUEST);
        });

        btnDaftar.setOnClickListener(v -> {
            String nama = edtNama.getText().toString().trim();
            String ktp = edtKTP.getText().toString().trim();
            String nik = edtNIK.getText().toString().trim();
            String departemen = edtDepartemen.getSelectedItem().toString();
            String password = edtPassword.getText().toString().trim();

            if (nama.isEmpty() || ktp.isEmpty() || nik.isEmpty() || departemen.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (base64KTP.isEmpty()) {
                Toast.makeText(this, "Silakan upload foto KTP terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User(nama, ktp, nik, departemen, password);
            long result = dbHelper.insertUser(user);
            if (result > 0) {
                Toast.makeText(this, "Pendaftaran berhasil!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Pendaftaran gagal. NIK mungkin sudah terdaftar.", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    Bitmap bitmap = android.graphics.BitmapFactory.decodeStream(inputStream);
                    imgKTPPreview.setImageBitmap(bitmap);
                    imgKTPPreview.setVisibility(ImageView.VISIBLE);

                    // Convert to Base64
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                    byte[] imageBytes = baos.toByteArray();
                    base64KTP = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                } catch (IOException e) {
                    Toast.makeText(this, "Gagal membuka gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
