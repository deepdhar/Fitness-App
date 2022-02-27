package com.example.fitnessapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.utils.GoalDatabaseClass;
import com.example.fitnessapp.R;

// updated 12:59 AM 23.02.22
public class GoalsFragment extends Fragment {

    private EditText goalNameEt, goalDateEt, goalStepsEt;
    private TextView okButton, completedPercentTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_goals, container, false);

        goalNameEt = view.findViewById(R.id.goalNameEt);
        goalDateEt = view.findViewById(R.id.goalDateEt);
        goalStepsEt = view.findViewById(R.id.goalStepsEt);
        okButton = view.findViewById(R.id.goalsOkButton);

        okButton.setOnClickListener(view1 -> {
            GoalDatabaseClass myDb = new GoalDatabaseClass(getContext());
            myDb.addGoals(goalNameEt.getText().toString().trim(),
                    goalDateEt.getText().toString().trim(),
                    Integer.parseInt(goalStepsEt.getText().toString().trim()));
            goalNameEt.setText("");
            goalDateEt.setText("");
            goalStepsEt.setText("");
        });

        return view;
    }

}
