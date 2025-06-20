package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.R;

public class EdukasiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edukasi);

        Button btnLihat = findViewById(R.id.btnLihat);
        btnLihat.setOnClickListener(v -> {
            Intent intent = new Intent(this, EdukasiDetailActivity.class);
            startActivity(intent);
        });
    }
}
