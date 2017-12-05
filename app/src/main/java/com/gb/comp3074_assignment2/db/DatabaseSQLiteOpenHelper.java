package com.gb.comp3074_assignment2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dylan on 2017-11-23.
 */

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medical.db";
    private static final int VERSION_NUMBER = 1;

    public DatabaseSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MedicalContract.CREATE_PATIENT_ENTRY_TABLE);
        db.execSQL(MedicalContract.CREATE_TEST_ENTRY_TABLE);
        db.execSQL(MedicalContract.CREATE_NURSE_ENTRY_TABLE);
        db.execSQL(MedicalContract.CREATE_DOCTOR_ENTRY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MedicalContract.PatientEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedicalContract.TestEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedicalContract.NurseEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MedicalContract.DoctorEntry.TABLE_NAME);

        onCreate(db);
    }
}
