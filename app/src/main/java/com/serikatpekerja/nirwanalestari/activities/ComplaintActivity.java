package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.Laporan;

public class ComplaintActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;

    EditText edtNama, edtNIK, edtIsi;
    Spinner spinnerDepartemen;
    Button btnKirim, btnBack, btnPilihFile;
    TextView txtFileTerpilih;
    DatabaseHelper dbHelper;

    Uri fileUri = null;
    String fileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        edtNama = findViewById(R.id.edtNama);
        edtNIK = findViewById(R.id.edtNIK);
        edtIsi = findViewById(R.id.edtIsi);
        spinnerDepartemen = findViewById(R.id.spinnerDepartemen);
        btnKirim = findViewById(R.id.btnKirim);
        btnBack = findViewById(R.id.btnBack);
        btnPilihFile = findViewById(R.id.btnPilihFile);
        txtFileTerpilih = findViewById(R.id.txtFileTerpilih);

        dbHelper = new DatabaseHelper(this);

        // Tombol pilih file
        btnPilihFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*"); // semua jenis file
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

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

                // Catatan: Di tahap lanjutan, fileUri bisa dikirim ke server atau disimpan lokal
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
            fileName = getFileName(fileUri);
            txtFileTerpilih.setText("File: " + fileName);
        }
    }

    private String getFileName(Uri uri) {
        String result = "";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return result;
    }
}
