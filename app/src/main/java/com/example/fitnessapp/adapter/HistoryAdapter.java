package com.example.fitnessapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.utils.HistoryDatabaseClass;
import com.example.fitnessapp.R;
import com.example.fitnessapp.fragments.HomeFragment;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList goal_id_history, goal_name_history, goal_date_history, goal_steps_history;

    int position;

    public HistoryAdapter(Context context, ArrayList goal_id_history,
                          ArrayList goal_name_history, ArrayList goal_date_history,
                          ArrayList goal_steps_history) {
        this.context = context;
        this.goal_id_history = goal_id_history;
        this.goal_name_history = goal_name_history;
        this.goal_date_history = goal_date_history;
        this.goal_steps_history = goal_steps_history;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.historyNameTv.setText(String.valueOf(goal_name_history.get(position)));
        holder.historyDateTv.setText(String.valueOf(goal_date_history.get(position)));
        holder.historyStepsTv.setText(String.valueOf(goal_steps_history.get(position)));

        holder.deleteButton.setOnClickListener(view -> {
            String historyName = holder.historyNameTv.getText().toString().trim();

            // confirm dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Delete " + historyName + "?");
            builder.setMessage("Are you sure you want to delete this from history?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //delete activity and move to home fragment
                    HistoryDatabaseClass historyDb = new HistoryDatabaseClass(view.getContext());
                    historyDb.deleteOneRow(historyName);
                    HomeFragment fragment = new HomeFragment();
                    ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, fragment).commit();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();

        });

    }

    public Context getContext() {
        return (AppCompatActivity)context;
    }

    @Override
    public int getItemCount() {
        return goal_id_history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView historyNameTv, historyDateTv, historyStepsTv;
        ImageView deleteButton;
        CardView cardViewHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyNameTv = itemView.findViewById(R.id.item_name_history);
            historyDateTv = itemView.findViewById(R.id.item_date_history);
            historyStepsTv = itemView.findViewById(R.id.item_steps_history);
            deleteButton = itemView.findViewById(R.id.item_delete_history);
            cardViewHistory = itemView.findViewById(R.id.itemCardLayoutHistory);
        }
    }

    void confirmDialog(String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HistoryDatabaseClass historyDb = new HistoryDatabaseClass(context.getApplicationContext());
                historyDb.deleteOneRow(name);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
