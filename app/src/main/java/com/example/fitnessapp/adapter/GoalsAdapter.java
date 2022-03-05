package com.example.fitnessapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.SetGoalActivity;
import com.example.fitnessapp.utils.GoalDatabaseClass;

import java.util.ArrayList;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {

    private Context context;
    private ArrayList goal_id, goal_name, goal_date, goal_steps;

    int position;

    public GoalsAdapter(Context context, ArrayList goal_id, ArrayList goal_name, ArrayList goal_date, ArrayList goal_steps) {
        this.context = context;
        this.goal_id = goal_id;
        this.goal_name = goal_name;
        this.goal_date = goal_date;
        this.goal_steps = goal_steps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_goals, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.goalNameTv.setText(String.valueOf(goal_name.get(position)));
        holder.goalDateTv.setText(String.valueOf(goal_date.get(position)));
        holder.goalStepsTv.setText(String.valueOf(goal_steps.get(position)));

        //when a goal card is selected, it opens up for confirmation
        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, SetGoalActivity.class);
            intent.putExtra("goalId", String.valueOf(goal_id.get(position)));
            intent.putExtra("goalName", String.valueOf(goal_name.get(position)));
            intent.putExtra("goalDate", String.valueOf(goal_date.get(position)));
            intent.putExtra("goalSteps", String.valueOf(goal_steps.get(position)));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return goal_id.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView goalNameTv, goalDateTv, goalStepsTv;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goalNameTv = itemView.findViewById(R.id.item_name);
            goalDateTv = itemView.findViewById(R.id.item_date);
            goalStepsTv = itemView.findViewById(R.id.item_steps);
            cardView = itemView.findViewById(R.id.itemCardLayout);
        }
    }
}
