package com.serikatpekerja.nirwanalestari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView txtWelcome;
    GridLayout gridMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);
        gridMenu = findViewById(R.id.gridMenu);

        // Ambil nama user dari intent (misal dikirim dari LoginActivity)
        String userName = getIntent().getStringExtra("user_name");
        if (userName != null) {
            txtWelcome.setText("Selamat datang, " + userName.toUpperCase());
        }

        setupGridMenu();
    }

    private void setupGridMenu() {
        // Tombol 0: Pengaduan
        LinearLayout pengaduanBtn = (LinearLayout) gridMenu.getChildAt(0);
        pengaduanBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, ComplaintActivity.class));
        });

        // Tambahkan item lainnya jika sudah di-layout-kan
        // Misal: edukasiBtn.setOnClickListener( ... );
    }
}
