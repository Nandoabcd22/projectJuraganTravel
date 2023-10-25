package com.nando.juragantravel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button jamaahButton = findViewById(R.id.btnJamaah);
        jamaahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LogJamaahActivity.class);
                startActivity(intent);
            }
        });

        // Find the "Agen" button and set an OnClickListener
        Button agenButton = findViewById(R.id.btnAgen);
        agenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LogAgenActivity.class);
                startActivity(intent);
            }
        });

    }
}
