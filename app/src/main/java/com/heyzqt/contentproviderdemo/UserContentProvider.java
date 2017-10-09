package com.heyzqt.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
        switch (mUriMatcher.match(uri)) {
            case USER_NAME:
                break;
            case USER_AGE:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
