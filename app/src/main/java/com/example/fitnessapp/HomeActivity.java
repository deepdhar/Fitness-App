package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    private TextView textView;
    private ProgressBar circleProgressBar;
    private SeekBar seekBar;
    private TextView okButton, goalsTv, goalTvHome, completionPercentTv, stepsCompletedTv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        textView = findViewById(R.id.goalsTv);
//        progressBar = findViewById(R.id.goalsProgressBar);
        seekBar = findViewById(R.id.goalsSeekBar);
        okButton = findViewById(R.id.goalsOkButton);
        goalsTv = findViewById(R.id.goalsTv);
        goalTvHome = findViewById(R.id.goalTvHome);
        circleProgressBar = findViewById(R.id.circleProgressBar);
        completionPercentTv = findViewById(R.id.completionPercentTv);
        stepsCompletedTv = findViewById(R.id.stepsCompleted);

        int stepsCompleted = Integer.parseInt(stepsCompletedTv.getText().toString());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                progressBar.setProgress(progress);
                textView.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        okButton.setOnClickListener(view -> {
            String stepsValue = goalsTv.getText().toString().trim();
            //max steps value (goal)
            int steps = Integer.parseInt(stepsValue);
            goalTvHome.setText(stepsValue);
            circleProgressBar.setMax(steps);

            float div = (float) stepsCompleted/steps;
            int percent = Math.round(div*100);
            completionPercentTv.setText(String.valueOf(percent) + "%");

            closeDrawer(drawerLayout);
        });

    }

    public void onClickGoals(View view) {
        //Open drawer
        drawerLayout.openDrawer(GravityCompat.START);
//        openDrawer(drawerLayout);
    }

    public void closeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void onClickHistory(View view) {
        drawerLayout.openDrawer(GravityCompat.END);
    }

}