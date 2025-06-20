package com.serikatpekerja.nirwanalestari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.activities.ChatbotActivity;
import com.serikatpekerja.nirwanalestari.activities.ComplaintActivity;
import com.serikatpekerja.nirwanalestari.activities.EdukasiActivity;
import com.serikatpekerja.nirwanalestari.activities.HistoryActivity;
import com.serikatpekerja.nirwanalestari.activities.WebViewActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView txtWelcome;
    GridLayout gridMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtWelcome);
        gridMenu = findViewById(R.id.gridMenu);

        // Ambil nama user dari intent
        String userName = getIntent().getStringExtra("user_name");
        if (userName != null) {
            txtWelcome.setText("Selamat datang, " + userName.toUpperCase());
        }

        setupGridMenu();
    }

    private void setupGridMenu() {
        if (gridMenu.getChildCount() >= 5) {
            // Button 0: Pengaduan
            LinearLayout pengaduanBtn = (LinearLayout) gridMenu.getChildAt(0);
            pengaduanBtn.setOnClickListener(v ->
                startActivity(new Intent(this, ComplaintActivity.class))
            );

            // Button 1: Edukasi Pekerja
            LinearLayout edukasiBtn = (LinearLayout) gridMenu.getChildAt(1);
            edukasiBtn.setOnClickListener(v ->
                startActivity(new Intent(this, EdukasiActivity.class))
            );

            // Button 2: Chatbot
            LinearLayout chatbotBtn = (LinearLayout) gridMenu.getChildAt(2);
            chatbotBtn.setOnClickListener(v ->
                startActivity(new Intent(this, ChatbotActivity.class))
            );

            // Button 3: Info Ketenagakerjaan (WebView Kemnaker)
            LinearLayout infoBtn = (LinearLayout) gridMenu.getChildAt(3);
            infoBtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, WebViewActivity.class);
                intent.putExtra("url", "https://kemnaker.go.id/");
                startActivity(intent);
            });

            // Button 4: Riwayat Pengaduan
            LinearLayout historyBtn = (LinearLayout) gridMenu.getChildAt(4);
            historyBtn.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class))
            );
        }
    }
}
