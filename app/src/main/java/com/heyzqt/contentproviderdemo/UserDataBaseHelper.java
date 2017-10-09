package com.heyzqt.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by heyzqt on 2017/10/8.
 */
public class UserDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "heyzqt";

    private static final String TABLE_NAME = "users";

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_NAME +                       // Table's name
            "(" +                           // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            " USER_NAME TEXT," +
            " USER_AGE TEXT )";

    UserDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    UserDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MAIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
