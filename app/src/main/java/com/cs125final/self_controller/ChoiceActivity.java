package com.cs125final.self_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Button button1 = findViewById(R.id.min25);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openMainActivity();
                //beginCounter.settText("Done and Start");
            }
        });
        Button button2 = findViewById(R.id.min45);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openMain2Activity();
                //beginCounter.settText("Done and Start");
            }
        });
        Button button3 = findViewById(R.id.min60);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                openMain3Activity();
                //beginCounter.settText("Done and Start");
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openMain2Activity() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
    public void openMain3Activity() {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
}
