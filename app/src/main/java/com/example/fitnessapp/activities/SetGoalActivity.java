package com.example.fitnessapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.utils.GoalDatabaseClass;
import com.example.fitnessapp.utils.HistoryDatabaseClass;
import com.example.fitnessapp.R;

public class SetGoalActivity extends AppCompatActivity {

    TextView goalName, goalDate, goalSteps;
    TextView updateButton, deleteButton;
    CardView setButton;

    String id, name, date, steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        goalName = findViewById(R.id.goalNameEt2);
        goalDate = findViewById(R.id.goalDateEt2);
        goalSteps = findViewById(R.id.goalStepsEt2);
        updateButton = findViewById(R.id.setGoal_updateButton);
        deleteButton = findViewById(R.id.setGoal_deleteButton);
        setButton = findViewById(R.id.setButtonTv);

        getAndSetIntentData();

        //update goals
        updateButton.setOnClickListener(view -> {
            name = goalName.getText().toString().trim();
            date = goalDate.getText().toString().trim();
            steps = goalSteps.getText().toString().trim();
            GoalDatabaseClass goalDb = new GoalDatabaseClass(this);
            goalDb.updateData(id, name, date, steps);

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //delete goals
        deleteButton.setOnClickListener(view -> {
            GoalDatabaseClass goalDb = new GoalDatabaseClass(this);
            goalDb.deleteOneRow(id);

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        // goals deleted from HomeFragment and moved to History
        setButton.setOnClickListener(view -> {

            //add data in history database
            HistoryDatabaseClass myDb = new HistoryDatabaseClass(this);
            myDb.addHistory(goalName.getText().toString(),
                    goalDate.getText().toString(),
                    Integer.parseInt(goalSteps.getText().toString()));

            //delete data from main list after moving to history database
            GoalDatabaseClass goalDb = new GoalDatabaseClass(this);
            goalDb.deleteOneRow(id);

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("steps", String.valueOf(goalSteps.getText()));
            startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            recreate();
        }
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("goalId") && getIntent().hasExtra("goalName") && getIntent().hasExtra("goalDate")
                && getIntent().hasExtra("goalSteps")) {
            //Getting Data from intent
            id = getIntent().getStringExtra("goalId");
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