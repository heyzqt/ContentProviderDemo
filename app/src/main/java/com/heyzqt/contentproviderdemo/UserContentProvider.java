package com.heyzqt.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by heyzqt on 2017/10/8.
 */

public class UserContentProvider extends ContentProvider {

    static final String SCHEME = "content://";

    static final String AUTHORITY = "com.heyzqt.contentproviderdemo.provider";

    static final String PATH = "";

    static final Uri URI = Uri.parse(SCHEME + AUTHORITY + "/users");

    private static final UriMatcher mUriMatcher;

    private static final int USERS = 0x98;
    private static final int USERS_ID = 0x99;
    private static final int USER_NAME = 0x100;
    private static final int USER_AGE = 0x101;

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String AGE = "age";

    private Context mContext;

    private UserDataBaseHelper mDatabaseHelper;

    private SQLiteDatabase mUsersDB;

    private static final String TAG = "UserContentProvider";

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "users", USERS);
        mUriMatcher.addURI(AUTHORITY, "users/#", USERS_ID);
        mUriMatcher.addURI(AUTHORITY, "users/#", USER_NAME);
        mUriMatcher.addURI(AUTHORITY, "users/#", USER_AGE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDatabaseHelper = new UserDataBaseHelper(mContext);
        mUsersDB = mDatabaseHelper.getWritableDatabase();
        return (mUsersDB != null);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (mUriMatcher.match(uri)) {
            case USERS:
                queryBuilder.setTables(UserDataBaseHelper.TABLE_NAME);
                break;
            case USER_NAME:
                break;
            case USER_AGE:
                break;
        }
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(mUsersDB, strings, s, strings1, s1, null, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        switch (mUriMatcher.match(uri)) {
            case USERS:
                long rowID = mUsersDB.insert(UserDataBaseHelper.TABLE_NAME, null, contentValues);
                if (rowID > 0) {
                    return ContentUris.withAppendedId(URI, rowID);
                }
                break;
            case USERS_ID:
                break;
            case USER_NAME:
                break;
            case USER_AGE:
                break;
            default:
                Log.i(TAG, "insert: can not recognise the uri");
                throw new IllegalArgumentException("Unknown URI : " + URI);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case USERS:
                count = mUsersDB.delete(UserDataBaseHelper.TABLE_NAME, s, strings);
                Log.i(TAG, "delete: count = " + count);
                break;
            default:
                Log.i(TAG, "insert: can not recognise the uri");
                throw new IllegalArgumentException("Unknown URI : " + URI);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case USERS:
                count = mUsersDB.update(UserDataBaseHelper.TABLE_NAME, contentValues, s, strings);
                Log.i(TAG, "update: count = " + count);
                break;
            default:
                Log.i(TAG, "insert: can not recognise the uri");
                throw new IllegalArgumentException("Unknown URI : " + URI);
        }
        return count;
    }
}
