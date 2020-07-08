package com.example.boxingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class timerPage extends AppCompatActivity {
    private long mTimeLeftInMillis;
    private long startTime;
    private int numrounds;
    /*
    private String roundsNum;
    private String roundLen;
    private String restLen;

     */

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer restTimer;

    private boolean mTimerRunning;
    private int count = 0;
    private long restTime;
    private boolean boxOrRest;
    private long time;
    private long restLeft;

    MediaPlayer player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_page);

        /*
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String roundsNum = extras.getString("roundsNum");
        }

        Bundle extras2 = getIntent().getExtras();
        if (extras2 != null) {
            String roundLen = extras.getString("roundLen");
        }

        Bundle extras3 = getIntent().getExtras();
        if (extras3 != null) {
            String restLen = extras.getString("restLen");

                }

         */

        Intent intent = getIntent();
        String roundNum = intent.getStringExtra("key1");
        String roundLen = intent.getStringExtra("key2");
        String restLen = intent.getStringExtra("key3");

        numrounds = Integer.parseInt(roundNum);


        switch (roundLen){
            case "1":
                mTimeLeftInMillis = 60000;
                break;
            case "2":
                mTimeLeftInMillis = 120000;
                break;
            case "3":
                mTimeLeftInMillis = 180000;
                break;
            case "4":
                mTimeLeftInMillis = 240000;
                break;
            case "5":
                mTimeLeftInMillis = 300000;
                break;
            case "6":
                mTimeLeftInMillis = 360000;
                break;
            case "7":
                mTimeLeftInMillis = 420000;
                break;
            case "8":
                mTimeLeftInMillis = 480000;
                break;
            case "9":
                mTimeLeftInMillis = 540000;
                break;
            case "10":
                mTimeLeftInMillis = 600000;
                break;
        }
        startTime = mTimeLeftInMillis;

        switch (restLen){
            case "1":
                restTime = 60000;
                break;
            case "2":
                restTime = 120000;
                break;
            case "3":
                restTime = 180000;
                break;
            case "4":
                restTime = 240000;
                break;
            case "5":
                restTime = 300000;
                break;
        }
        restLeft = restTime;

        boxOrRest = true;

        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        updateCountDownText();

    }


    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //timerRunning = false;
                play();
                mTimeLeftInMillis = startTime;
                count++;
                if (boxOrRest && count < numrounds) {
                    setRestTimer();
                }
            }


        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("STOP");
    }

    public void setRestTimer(){
        boxOrRest = !boxOrRest;
        restTimer = new CountDownTimer(restLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                restLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                play();
                restLeft = restTime;
                boxOrRest = !boxOrRest;
                startTimer();

            }
        }.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");

    }

    private void updateCountDownText() {
        if (boxOrRest){
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            mTextViewCountDown.setText(timeLeftFormatted);
        } else {
            int minutes = (int) (restLeft / 1000) / 60;
            int seconds = (int) (restLeft / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

            mTextViewCountDown.setText(timeLeftFormatted);
        }

    }

    public void play() {
        if (player == null) {
            player = MediaPlayer.create(this,R.raw.noise);
        }

        player.start();
    }

}