package com.eller.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button zbrajanje, oduzimanje, mnozenje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zbrajanje = findViewById(R.id.btnDodaj);
        oduzimanje = findViewById(R.id.btnOduzmi);
        mnozenje = findViewById(R.id.btnMnozi);

        zbrajanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        oduzimanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mnozenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}