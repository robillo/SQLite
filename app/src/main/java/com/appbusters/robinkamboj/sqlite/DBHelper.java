package com.appbusters.robinkamboj.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

    //CREATE
    public Boolean insertContact(String name, String phone, String email, String street, String place){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name ", name);
        contentValues.put("phone ", phone);
        contentValues.put("email ", email);
        contentValues.put("street ", street);
        contentValues.put("place ", place);
        sqLiteDatabase.insert("contacts", null, contentValues);
        return true;
    }

    //READ
    public Cursor getData(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME +
        " WHERE " + CONTACTS_COLUMN_ID + "=" + id + " ", null);
        return cursor;
    }

    //UPDATE
    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_PHONE, phone);
        contentValues.put(CONTACTS_COLUMN_EMAIL, email);
        contentValues.put(CONTACTS_COLUMN_STREET, street);
        contentValues.put(CONTACTS_COLUMN_PLACE, place);
        db.update(CONTACTS_TABLE_NAME, contentValues, CONTACTS_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    //DELETE
    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME,
                CONTACTS_COLUMN_ID + "= ? ",
                new String[] { Integer.toString(id) });
    }


    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "SELECT * FROM " + CONTACTS_TABLE_NAME + " ", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public int numberOfRows(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, CONTACTS_TABLE_NAME);
        return numRows;
    };
}