package com.serikatpekerja.nirwanalestari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.serikatpekerja.nirwanalestari.activities.LoginActivity;
import com.serikatpekerja.nirwanalestari.activities.RegistrationActivity;
import com.serikatpekerja.nirwanalestari.activities.ComplaintActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnRegister, btnComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnComplaint = findViewById(R.id.btnComplaint);

        btnLogin.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, LoginActivity.class))
        );

        btnRegister.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class))
        );

        btnComplaint.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this, ComplaintActivity.class))
        );
    }
}
