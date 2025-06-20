package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 6000; // 6 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        VideoView splashVideo = findViewById(R.id.splashVideo);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_splash_compressed);
        splashVideo.setVideoURI(videoUri);
        splashVideo.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
