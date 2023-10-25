package com.nando.juragantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogJamaahActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Button loginButton;

    private EditText nikEditText;
    private boolean isNikVisible = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_jamaah);

        TextView daftarTextView = findViewById(R.id.btnDaftar);
        daftarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogJamaahActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this);
        loginButton = findViewById(R.id.loginButton);
        nikEditText = findViewById(R.id.nik);

        // Tambahkan onTouchListener untuk ikon Show/Hide NIK
        nikEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (nikEditText.getRight() - nikEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        toggleNikVisibility(nikEditText);
                        return true;
                    }
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String providedNIK = nikEditText.getText().toString();

                if (databaseHelper.checkNIK(providedNIK)) {
                    // NIK valid, izinkan login ke activity_home_jamaah
                    Intent intent = new Intent(LogJamaahActivity.this, HomeJamaahActivity.class);
                    startActivity(intent);
                    Toast.makeText(LogJamaahActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    // NIK tidak valid, tampilkan pesan kesalahan atau lakukan tindakan yang sesuai
                    Toast.makeText(LogJamaahActivity.this, "NIK Tidak Valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void toggleNikVisibility(EditText editText) {
        if (isNikVisible) {
            editText.setInputType(129); // InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_off_24, 0);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_visibility_24, 0);
        }

        isNikVisible = !isNikVisible;
    }
}
