package com.serikatpekerja.nirwanalestari.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        VideoView splashVideo = findViewById(R.id.splashVideo);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_splash);
        splashVideo.setVideoURI(videoUri);

        splashVideo.setOnCompletionListener(mp -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        });

        splashVideo.start();
    }
}
