package com.nanodegree.android.physicalactivityhabittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nanodegree.android.physicalactivityhabittracker.Data.HabitContract.UserEntry;
import com.nanodegree.android.physicalactivityhabittracker.Data.HabitTrackerDBHelper;

import static com.nanodegree.android.physicalactivityhabittracker.Data.HabitContract.UserEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {
    /** Database helper that will provide us access to db*/
    private HabitTrackerDBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitTrackerDBHelper(this);
        Button addButton = (Button) findViewById(R.id.add_user_button);
        Button deleteTableButton = (Button) findViewById(R.id.delete_table_button);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertUser();
                displayDatabaseInfo();
            }
        });

        deleteTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deleteData();
                displayDatabaseInfo();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        insertUser();
        displayDatabaseInfo();
    }

    public Cursor queryAllHabits() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        HabitTrackerDBHelper mDbHelper = new HabitTrackerDBHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_DATE_HABIT,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_EXERCISE_ACTIVITY,
                UserEntry.COLUMN_USER_GENDER,
                UserEntry.COLUMN_USER_WEIGHT,
                UserEntry.COLUMN_NUMBER_REPS,
                UserEntry.COLUMN_DURATION_ACTIVITY
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        
        return cursor;
    }
    
        /**
         * Temporary helper method to READ information in the onscreen TextView about the state of
         * the users database.
         */
    private void displayDatabaseInfo() {

        Cursor cursor = queryAllHabits();
        
        TextView displayView = (TextView) findViewById(R.id.text_view_user);

        displayView.setText(""); //set view as blank first, then append data to prevent repeats of data.

        try {
            int idColumnIndex = cursor.getColumnIndex(UserEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_DATE_HABIT);
            int nameColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_NAME);
            int exerciseActivityColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_EXERCISE_ACTIVITY);
            int genderColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_USER_WEIGHT);
            int repetitionColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_NUMBER_REPS);
            int timeDurationColumnIndex = cursor.getColumnIndex(UserEntry.COLUMN_DURATION_ACTIVITY);

            //iterate through all returned rows in cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentExercise = cursor.getString(exerciseActivityColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);
                int currentReps = cursor.getInt(repetitionColumnIndex);
                int currentDuration = cursor.getInt(timeDurationColumnIndex);
                displayView.append(("\n" + currentID + " -" +
                        currentDate + " - " +
                        currentName + " - " +
                        currentExercise + " -" +
                        currentGender + " - " +
                        currentWeight + " - " +
                        currentReps + " - " +
                        currentDuration));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
    public void deleteData()
    {
        try
        {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            //delete
            db.delete(TABLE_NAME,null,null);
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    /**
     * Helper method to INSERT hardcoded User data into the database.
     */
    private void insertUser(){
        TextView displayView = (TextView) findViewById(R.id.text_view_user);
        displayView.setText("");
        //Gets database in WRITE MODE
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create ContentValues object where column names are the KEYS,
        // Values are the determined by you.
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_DATE_HABIT, "05/05/2001");
        values.put(UserEntry.COLUMN_USER_NAME, "Jayvizzle");
        values.put(UserEntry.COLUMN_USER_GENDER, UserEntry.GENDER_MALE);
        values.put(UserEntry.COLUMN_EXERCISE_ACTIVITY, "Pushups");
        values.put(UserEntry.COLUMN_USER_WEIGHT, 150);
        values.put(UserEntry.COLUMN_NUMBER_REPS, 15);
        values.put(UserEntry.COLUMN_DURATION_ACTIVITY, 15);

        db.insert(TABLE_NAME, null, values);
    }

}
