package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.User;

public class ProfilPekerjaActivity extends AppCompatActivity {

    TextView txtNama, txtNIK, txtKTP, txtDepartemen;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pekerja);

        txtNama = findViewById(R.id.txtNama);
        txtNIK = findViewById(R.id.txtNIK);
        txtKTP = findViewById(R.id.txtKTP);
        txtDepartemen = findViewById(R.id.txtDepartemen);
        dbHelper = new DatabaseHelper(this);

        String nik = getIntent().getStringExtra("nik");
        if (nik != null) {
            User user = dbHelper.getUserByNIK(nik);
            if (user != null) {
                txtNama.setText(user.getNama());
                txtNIK.setText(user.getNik());
                txtKTP.setText(user.getKtp());
                txtDepartemen.setText(user.getDepartemen());
            }
        }
    }
}
