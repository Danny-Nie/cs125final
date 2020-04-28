package com.cs125final.self_controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //setVisible();
        Button beginCounter = findViewById(R.id.beginCounter);
        beginCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openChoiceActivity();
                //beginCounter.settText("Done and Start");
            }
        });
    }
    public void openChoiceActivity() {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
    }

}
