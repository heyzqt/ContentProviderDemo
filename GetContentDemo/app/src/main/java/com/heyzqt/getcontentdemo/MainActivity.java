package com.heyzqt.getcontentdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;

    ContentResolver mContentResolver;

    static final String AUTHORITY = "com.heyzqt.contentproviderdemo.provider";

    static final String SCHEME = "content://";

    static final Uri URI = Uri.parse(SCHEME + AUTHORITY + "/users");

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mContentResolver = this.getContentResolver();

        Button insertBtn = (Button) findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(this);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);
        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(this);
        Button queryBtn = (Button) findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();
        switch (v.getId()) {
            case R.id.insertBtn:
                cv.put("user_name", "jack");
                cv.put("user_age", "18");
                Uri uri = mContentResolver.insert(URI, cv);
                Log.i(TAG, "onClick: uri = " + uri);
                break;
            case R.id.deleteBtn:
                int count = mContentResolver.delete(URI, "_id = ?", new String[]{"2"});
                Log.i(TAG, "onClick: count = " + count);
                break;
            case R.id.updateBtn:
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("user_age", "7");
                int updateCount = mContentResolver.update(URI, contentvalues, "_id = 3", null);
                Log.i(TAG, "onClick: update count = " + updateCount);
                break;
            case R.id.queryBtn:
                Cursor cursor = mContentResolver.query(URI, null, null, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("user_name"));
                    String age = cursor.getString(cursor.getColumnIndex("user_age"));
                    Log.i(TAG, "onClick: colume info = " + id + " , name = " + name + " , age = " + age);
                    cursor.moveToNext();
                }
                break;
        }
    }
}