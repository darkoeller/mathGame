package com.eller.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView txtScore, txtLife, txtTime, txtUpit;
    EditText txtOdgovor;
    Button btnOk, btnNext;
    int userAnswer;

    Random random = new Random();
    int broj1;
    int broj2;
    int praviOdgovor;
    int userScore = 0;
    int userLife = 3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 20000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtScore = findViewById(R.id.txtScore);
        txtLife = findViewById(R.id.txtLife);
        txtTime = findViewById(R.id.txtTime);
        txtUpit = findViewById(R.id.txtUpit);
        txtOdgovor = findViewById(R.id.edTextOdgovor);
        btnOk = findViewById(R.id.btnOk);
        btnNext = findViewById(R.id.btnNext);

        gameContinue();


        btnOk.setOnClickListener(view -> {
            if (txtOdgovor.getText().length() == 0){
                txtUpit.setText("Niste upisali odgovor.");
                return;
            }
            pauseTimer();
            btnOk.setClickable(false);

        userAnswer = Integer.parseInt(txtOdgovor.getText().toString());
        if (userAnswer == praviOdgovor){
            userScore += 10;
            txtScore.setText(""+ userScore);
            txtUpit.setText("Čestitam, tvoj odgovor je točan!");
            resetTimer();
        }else{
        userLife -= 1;
             txtLife.setText("" + userLife);
            txtUpit.setText("Loše, tvoj odgovor je pogrešan!");
        }

        });
        btnNext.setOnClickListener(view -> {
            btnOk.setClickable(true);
            txtOdgovor.setText("");
        resetTimer();
        if (userLife == 0){
            Toast.makeText(this, "Igra je gotova!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(GameActivity.this, ResultActivity.class);
            i.putExtra("score", userScore);
            startActivity(i);
            finish();
        }else{
            gameContinue();
        }
        });
    }
    public void gameContinue (){
     broj1 = random.nextInt(100);
     broj2 = random.nextInt(100);
     praviOdgovor = broj1 + broj2;
     txtUpit.setText(broj1 +" + " + broj2);
     startTimer();
    }
    public void startTimer(){
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long l) {
                time_left_in_milis = l;
                updateText();
            }
            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                userLife -= 1;
                updateText();
                txtLife.setText("" + userLife);
                txtUpit.setText("Jebi ga. Isteklo vrijeme.");
            }
        }.start();
        timer_running = true;
    }

    private void resetTimer() {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }

    private void pauseTimer() {
        timer.cancel();
        timer_running = false;
    }

    private void updateText() {
        int sec = (int) ((time_left_in_milis / 1000) % 60);
        String time_left = String.format(Locale.getDefault(), "%02d",sec);
        txtTime.setText(time_left);
    }
}