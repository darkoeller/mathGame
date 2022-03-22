package com.eller.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Button btnPonovi, btnIzlaz;
    TextView txtResult;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btnIzlaz = findViewById(R.id.btnIzlaz);
        btnPonovi = findViewById(R.id.btnPonovi);
        txtResult = findViewById(R.id.txtResult);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        String userscore = String.valueOf(score);
        txtResult.setText("Tvoj rezultat je: "+ userscore);

        btnPonovi.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        });
        btnIzlaz.setOnClickListener(view -> {
           finish();
        });
    }
}