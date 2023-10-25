package com.nando.juragantravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Registrations.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "registrations";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NIK = "nik";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_USERNAME = "username";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FULL_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_NIK + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_USERNAME + " TEXT);";
        db.execSQL(createTable);
    }

    public boolean checkNIK(String nik) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_NIK + " = ?";
        String[] selectionArgs = {nik};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Metode untuk menyimpan data pendaftaran
    public long insertRegistration(String fullName, String email, String nik, String phone, String gender, String address, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, fullName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NIK, nik);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_USERNAME, username);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
