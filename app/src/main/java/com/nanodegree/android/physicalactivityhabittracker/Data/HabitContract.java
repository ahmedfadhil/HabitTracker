package com.nanodegree.android.physicalactivityhabittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by jdifuntorum on 8/3/17.
 */

public final class HabitContract {

    public static abstract class UserEntry implements BaseColumns {

        public static final String TABLE_NAME = "UserData";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE_HABIT = "Date";
        public static final String COLUMN_USER_NAME = "Name";
        public static final String COLUMN_EXERCISE_ACTIVITY = "Activity";
        public static final String COLUMN_NUMBER_REPS = "Repetitions";
        public static final String COLUMN_DURATION_ACTIVITY= "Length";
        public static final String COLUMN_USER_GENDER = "Gender";
        public static final String COLUMN_USER_WEIGHT = "Weight";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }
}
