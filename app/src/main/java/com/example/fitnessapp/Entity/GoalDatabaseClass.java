package com.example.fitnessapp.Entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GoalDatabaseClass extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FitnessData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_fitness";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "goal_name";
    private static final String COLUMN_DATE = "goal_date";
    private static final String COLUMN_STEPS = "goal_steps";

    public GoalDatabaseClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + 
                COLUMN_DATE + " TEXT, " + 
                COLUMN_STEPS + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    public void addGoals(String name, String date, int steps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_STEPS, steps);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result==-1) {
            Toast.makeText(context, "something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added goal!", Toast.LENGTH_SHORT).show();
        }
    }
    
    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
