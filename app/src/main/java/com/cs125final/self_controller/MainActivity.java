package com.cs125final.self_controller;

import android.content.Intent;
import android.os.CountDownTimer;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.q42.android.scrollingimageview.ScrollingImageView;

import java.util.Locale;

/**
 * We used a portion of the code from the following website but did some heavy modification:
 *
https://codinginflow.com/tutorials/android/countdowntimer/part-1-countdown-timer
https://codinginflow.com/tutorials/android/alertdialog-interface
 */

public class MainActivity extends AppCompatActivity implements PauseDialog.PauseDialogListener, FinishedDialog.FinishedDialogListener {
    private static final long START_TIME_IN_MILLIS = 1500000;

    private TextView mTextViewCountDown;
    private TextView mcheat;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private ScrollingImageView mScrollingBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageResource(R.drawable.challen);

        mcheat = findViewById(R.id.cheat);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mScrollingBackground = findViewById(R.id.scrolling_background);
        mScrollingBackground.stop();
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                    openDialog();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFinishedDialog();
            }
        });

        updateCountDownText();
    }
    public void openFinishedDialog() {
        FinishedDialog finishedDialog = new FinishedDialog();
        finishedDialog.show(getSupportFragmentManager(), "finished dialog");
    }

    public void onBackClicked() {
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.putExtra("givenUp", 0);
        startActivity(intent);
    }

    public void openDialog() {
        PauseDialog dialog = new PauseDialog();
        dialog.show(getSupportFragmentManager(), "pause dialog");
    }

    @Override
    public void onYesClicked() {
        Intent intent = new Intent(this, ChoiceActivity.class);
        intent.putExtra("givenUp", 1);
        startActivity(intent);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("GiveUp");
        mButtonReset.setVisibility(View.INVISIBLE);
        mScrollingBackground.start();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mScrollingBackground.stop();
        mButtonStartPause.setText("Resume");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void resetTimer() {
        /** mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
         */
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
    @Override
    public void onBackPressed() {
        if (mTimerRunning) {
            pauseTimer();
            openDialog();
        } else {
            super.onBackPressed();
        }
    }
}