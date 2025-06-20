// File: app/src/main/java/com/serikatpekerja/nirwanalestari/activities/SplashActivity.java
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

        VideoView videoView = findViewById(R.id.videoViewSplash);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video; // Ganti "video" dengan nama file Anda
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoView.start();
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        // Langsung ke halaman Login setelah video selesai
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
