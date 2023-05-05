package com.example.stds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;

public class third_page extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;
    EditText timers ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_page);
        countdownTextView = findViewById(R.id.counter);
        timers = findViewById(R.id.counter_text);
         EditText counter_sub = findViewById(R.id.counter_sub);
         Button btn = findViewById(R.id.counter_btn);
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int timeInMillis = Integer.parseInt(String.valueOf(timers.getText()))*1000;
                        countDownTimer = new CountDownTimer(timeInMillis, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                // Update the countdown timer display

                                int secondsLeft = (int) (millisUntilFinished / 1000);
                                Duration duration = Duration.ofSeconds(secondsLeft);
                                long minutes = duration.toMinutes();
                                counter_sub.setVisibility(View.GONE);
                                timers.setVisibility(View.GONE);
                                btn.setVisibility(View.GONE);
                                countdownTextView.setVisibility(View.VISIBLE);
                                long rem = secondsLeft%60 ;
                                String formattedDuration = String.format("%02d:%02d", minutes, rem);
                                countdownTextView.setText(formattedDuration);
                                countdownTextView.setTextSize(120);

                            }
                            @Override
                            public void onFinish() {
                                countdownTextView.setTextSize(70);
                                Toast.makeText(third_page.this, counter_sub.getText()+" session is over well done !", Toast.LENGTH_SHORT).show();
                                counter_sub.setVisibility(View.VISIBLE);
                                counter_sub.setText("");
                                timers.setVisibility(View.VISIBLE);
                                timers.setText("");
                                btn.setVisibility(View.VISIBLE);
                                countdownTextView.setVisibility(View.GONE);
                                final MediaPlayer mediaPlayer = MediaPlayer.create(third_page.this,R.raw.ring);
                                    mediaPlayer.start();
                            }
                        };
                        countDownTimer.start();
                    }
                    });


}


}