package com.appbusters.robinkamboj.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by robinkamboj on 31/01/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_PLACE = "place";
    private HashMap hashMap;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
                CONTACTS_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                CONTACTS_COLUMN_NAME + " TEXT, " +
                CONTACTS_COLUMN_PHONE + " TEXT, " +
                CONTACTS_COLUMN_EMAIL + " TEXT, " +
                CONTACTS_COLUMN_STREET + " TEXT, " +
                CONTACTS_COLUMN_PLACE + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //CRUD METHODS: CREATE READ UPDATE DELETE

}