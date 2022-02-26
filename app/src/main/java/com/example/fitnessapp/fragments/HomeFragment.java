package com.example.fitnessapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Entity.GoalDatabaseClass;
import com.example.fitnessapp.R;
import com.example.fitnessapp.adapter.GoalsAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    String steps;

    ProgressBar circleProgressBar;
    TextView completedPercentTv, stepsCompletedTv, okButtonTv, todaysGoalTv;
    EditText enterStepsEditText;

    GoalDatabaseClass myDb;
    RecyclerView recyclerView;
    ArrayList<String> goal_id, goal_name, goal_date, goal_steps;
    GoalsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_home, container, false);

        circleProgressBar = view.findViewById(R.id.circleProgressBar);
        stepsCompletedTv = view.findViewById(R.id.stepsCompleted);
        todaysGoalTv = view.findViewById(R.id.todaysGoalTv);
        enterStepsEditText = view.findViewById(R.id.enterStepsEditText);
        okButtonTv = view.findViewById(R.id.okButtonTv);
        completedPercentTv = view.findViewById(R.id.completionPercentTv);
        recyclerView = view.findViewById(R.id.homeRecyclerView);


        // whenever the fragment gets created or resumed, all the values will be 0
        stepsCompletedTv.setText("0");
        circleProgressBar.setProgress(0);
        completedPercentTv.setText("0%");

        okButtonTv.setOnClickListener(view1 -> {
            if (!enterStepsEditText.getText().toString().matches("") && !todaysGoalTv.getText().toString().matches("0")) {
                stepsCompletedTv.setText(enterStepsEditText.getText().toString().trim());
                int stepsCompleted = Integer.parseInt(enterStepsEditText.getText().toString().trim());
                circleProgressBar.setProgress(stepsCompleted);

                int stepsInt = Integer.parseInt(steps);
                float completedPercentFt = (float) stepsCompleted / stepsInt; //0.5
                long completedPercent = (long) (completedPercentFt * 100);  //50
                completedPercentTv.setText(completedPercent + "%");

                enterStepsEditText.setText("");
                enterStepsEditText.clearFocus();

//                HomeDatabaseClass myDb = new HomeDatabaseClass(getContext());
//                myDb.addHomeData(stepsCompleted,
//                        Integer.parseInt(todaysGoalTv.getText().toString()),
//                        Integer.parseInt(String.valueOf(completedPercent)));

            } else if(todaysGoalTv.getText().toString().matches("0")) {
                Toast.makeText(getContext(), "select a goal first", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "enter steps walked", Toast.LENGTH_SHORT).show();
            }
        });


        myDb = new GoalDatabaseClass(getContext());
        goal_id = new ArrayList<>();
        goal_name = new ArrayList<>();
        goal_date = new ArrayList<>();
        goal_steps = new ArrayList<>();

        storeDataInArrays();

        adapter = new GoalsAdapter(getContext(), goal_id, goal_name, goal_date, goal_steps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAndSetIntentData();

        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getContext(), "no data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                goal_id.add(cursor.getString(0));
                goal_name.add(cursor.getString(1));
                goal_date.add(cursor.getString(2));
                goal_steps.add(cursor.getString(3));
            }
        }
    }

    void getAndSetIntentData() {
        if(getActivity().getIntent().hasExtra("steps")) {
            //getting data from intent
            steps = getActivity().getIntent().getStringExtra("steps");
            todaysGoalTv.setText(steps);
            circleProgressBar.setMax(Integer.parseInt(steps));
        } else {
            todaysGoalTv.setText("0");
        }
    }

//    @Override
//    public void onDestroy() {
//        destroyFlag = 1;
//        super.onDestroy();
//    }

    @Override
    public void onResume() {
        super.onResume();
//        HomeDatabaseClass myDb = new HomeDatabaseClass(getContext());
//        Cursor cursor = myDb.readAllDataHome();
//        if (cursor.getCount() == 0) {
//            Toast.makeText(getContext(), "no data.", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()) {
//                stepsCompletedTv.setText(cursor.getString(1));
//                todaysGoalTv.setText(cursor.getString(2));
//                completedPercentTv.setText(cursor.getString(3) + "%");
//            }
//        }
    }
}
