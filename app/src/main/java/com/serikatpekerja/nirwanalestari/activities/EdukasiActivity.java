package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.R;

public class EdukasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);
    }

    public void openHakPekerja(View view) {
        Intent intent = new Intent(this, EdukasiDetailActivity.class);
        intent.putExtra("judul", "Hak-Hak Pekerja");
        intent.putExtra("isi", "Pekerja memiliki hak atas:\n" +
                "- Upah yang layak\n" +
                "- Jam kerja sesuai aturan\n" +
                "- Cuti dan waktu istirahat\n" +
                "- Keselamatan dan kesehatan kerja (K3)\n" +
                "- Perlindungan terhadap diskriminasi\n" +
                "- Hak untuk membentuk dan bergabung dalam serikat pekerja");
        startActivity(intent);
    }

    public void openSerikatPekerja(View view) {
        Intent intent = new Intent(this, EdukasiDetailActivity.class);
        intent.putExtra("judul", "Apa Itu Serikat Pekerja?");
        intent.putExtra("isi", "Serikat pekerja adalah organisasi yang dibentuk oleh dan untuk pekerja guna memperjuangkan hak dan kepentingan mereka, seperti:\n" +
                "- Negosiasi upah\n" +
                "- Perlindungan hukum\n" +
                "- Fasilitasi pengaduan\n" +
                "- Advokasi di tempat kerja");
        startActivity(intent);
    }
}
