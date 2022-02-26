package com.example.fitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Entity.HistoryDatabaseClass;
import com.example.fitnessapp.R;
import com.example.fitnessapp.fragments.HomeFragment;

public class SetGoalActivity extends AppCompatActivity {

    TextView goalName, goalDate, goalSteps;
    CardView setButton;

    String name, date, steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        goalDate = findViewById(R.id.item_date2);
        goalName = findViewById(R.id.item_name2);
        goalSteps = findViewById(R.id.item_steps2);
        setButton = findViewById(R.id.setButtonTv);

        getAndSetIntentData();

        // when set button is pressed, todaysGoalTv in HomeFragment is updated
        setButton.setOnClickListener(view -> {

            //add data in history database
            HistoryDatabaseClass myDb = new HistoryDatabaseClass(this);
            myDb.addHistory(goalName.getText().toString(),
                    goalDate.getText().toString(),
                    Integer.parseInt(goalSteps.getText().toString()));

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("steps", String.valueOf(goalSteps.getText()));
            startActivity(intent);
        });

    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("goalName") && getIntent().hasExtra("goalDate")
                && getIntent().hasExtra("goalSteps")) {
            //Getting Data from intent
            name = getIntent().getStringExtra("goalName");
            date = getIntent().getStringExtra("goalDate");
            steps = getIntent().getStringExtra("goalSteps");

            //Setting intent data
            goalName.setText(name);
            goalDate.setText(date);
            goalSteps.setText(steps);
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }
}