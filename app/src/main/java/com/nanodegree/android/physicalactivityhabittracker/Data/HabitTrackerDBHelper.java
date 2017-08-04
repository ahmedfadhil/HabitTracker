package com.nanodegree.android.physicalactivityhabittracker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.nanodegree.android.physicalactivityhabittracker.Data.HabitContract.UserEntry;


/**
 * Created by jdifuntorum on 8/3/17.
 */

public class HabitTrackerDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HabitTracker.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitContract.UserEntry.TABLE_NAME + "("
                    + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + UserEntry.COLUMN_DATE_HABIT + TEXT_TYPE + COMMA_SEP
                    + UserEntry.COLUMN_USER_NAME + TEXT_TYPE + "TEXT NOT NULL" + COMMA_SEP
                    + UserEntry.COLUMN_EXERCISE_ACTIVITY + TEXT_TYPE + "TEXT NOT NULL" + COMMA_SEP
                    + UserEntry.COLUMN_USER_GENDER + " INTEGER NOT NULL, "
                    + UserEntry.COLUMN_USER_WEIGHT + " INTEGER NOT NULL, "
                    + UserEntry.COLUMN_NUMBER_REPS + " INTEGER NOT NULL" + COMMA_SEP
                    + UserEntry.COLUMN_DURATION_ACTIVITY + " INTEGER NOT NULL DEFAULT 0);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public HabitTrackerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        // this database is only a cache for online data, so its upgrade policy
        // is simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}

