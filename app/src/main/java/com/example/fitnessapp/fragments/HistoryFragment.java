package com.example.fitnessapp.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.utils.HistoryDatabaseClass;
import com.example.fitnessapp.R;
import com.example.fitnessapp.adapter.HistoryAdapter;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    HistoryDatabaseClass myDb;
    RecyclerView historyRecyclerView;
    ArrayList<String> goal_id_history, goal_name_history, goal_date_history, goal_steps_history;
    HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_history, container, false);
        historyRecyclerView = view.findViewById(R.id.historyRecyclerView);

        myDb = new HistoryDatabaseClass(getContext());
        goal_id_history = new ArrayList<>();
        goal_name_history = new ArrayList<>();
        goal_date_history = new ArrayList<>();
        goal_steps_history = new ArrayList<>();

        storeDataInArrays();

        adapter = new HistoryAdapter(getContext(), goal_id_history, goal_name_history, goal_date_history, goal_steps_history);
        historyRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        historyRecyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void storeDataInArrays() {
        Cursor cursor = myDb.readAllDataHistory();
        if(cursor.getCount()==0) {
//            Toast.makeText(getContext(), "no data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                goal_id_history.add(cursor.getString(0));
                goal_name_history.add(cursor.getString(1));
                goal_date_history.add(cursor.getString(2));
                goal_steps_history.add(cursor.getString(3));
            }
        }
    }

}
