package com.gb.comp3074_assignment2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.MotionEvent;

import com.gb.comp3074_assignment2.classes.Doctor;
import com.gb.comp3074_assignment2.classes.Nurse;
import com.gb.comp3074_assignment2.classes.Patient;
import com.gb.comp3074_assignment2.classes.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dylan on 2017-11-23.
 */

public class MedicalDataSource {
    private static final String TAG = MedicalDataSource.class.getSimpleName();
    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    public MedicalDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    public void open() {
        this.database = dbHelper.getWritableDatabase();
        Log.d(TAG, "database is opened");
    }

    public void close() {
        dbHelper.close();
        Log.d(TAG, "database is closed");
    }

    public void createPatient(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(MedicalContract.PatientEntry.COLUMN_FIRST_NAME, patient.getFirstName());
        values.put(MedicalContract.PatientEntry.COLUMN_LAST_NAME, patient.getLastName());
        values.put(MedicalContract.PatientEntry.COLUMN_DEPARTMENT, patient.getDepartment());
        values.put(MedicalContract.PatientEntry.COLUMN_DOCTOR_ID, patient.getDoctorId());
        values.put(MedicalContract.PatientEntry.COLUMN_ROOM, patient.getRoom());
        long rowId = database.insert(MedicalContract.PatientEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Patient with ID" + rowId);
    }

    public void createNurse(Nurse nurse) {
        ContentValues values = new ContentValues();
        values.put(MedicalContract.NurseEntry.COLUMN_NURSE_ID, nurse.getId());
        values.put(MedicalContract.NurseEntry.COLUMN_FIRST_NAME, nurse.getFirstName());
        values.put(MedicalContract.NurseEntry.COLUMN_LAST_NAME, nurse.getLastName());
        values.put(MedicalContract.NurseEntry.COLUMN_DEPARTMENT, nurse.getDepartment());
        long rowId = database.insert(MedicalContract.NurseEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Nurse with ID" + rowId);
    }

    public void createDoctor(Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(MedicalContract.DoctorEntry.COLUMN_DOCTOR_ID, doctor.getId());
        values.put(MedicalContract.DoctorEntry.COLUMN_FIRST_NAME, doctor.getFirstName());
        values.put(MedicalContract.DoctorEntry.COLUMN_LAST_NAME, doctor.getLastName());
        values.put(MedicalContract.DoctorEntry.COLUMN_DEPARTMENT, doctor.getDepartment());
        long rowId = database.insert(MedicalContract.DoctorEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Doctor with ID" + rowId);
    }

    public void createTest(Test test) {
        ContentValues values = new ContentValues();
        values.put(MedicalContract.TestEntry.COLUMN_TEST_ID, test.getId());
        values.put(MedicalContract.TestEntry.COLUMN_PATIENT_ID, test.getPatientId());
        values.put(MedicalContract.TestEntry.COLUMN_BPL, test.getBpl());
        values.put(MedicalContract.TestEntry.COLUMN_BPH, test.getBph());
        values.put(MedicalContract.TestEntry.COLUMN_TEMPERATURE, test.getTemperature());
        values.put(MedicalContract.TestEntry.COLUMN_BLOOD_PRESSURE, test.getBloodPressure());
        long rowId = database.insert(MedicalContract.TestEntry.TABLE_NAME, null, values);
        Log.d(TAG, "Test with ID" + rowId);
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        String selectQuery = "SELECT * FROM patient";
        Cursor cursor = database.rawQuery(selectQuery, null);
        try {
            while (cursor.moveToNext()) {
                Patient patient = new Patient(
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_PATIENT_ID)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_DEPARTMENT)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_DOCTOR_ID)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_ROOM))
                );
                patients.add(patient);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return patients;
    }

    public List<Patient> getPatientByFirstName(String firstName) {
        String selection = "SELECT * FROM patient WHERE " + MedicalContract.PatientEntry.COLUMN_FIRST_NAME + " = ?";
        String[] selectionArgs = new String[]{firstName};

        List<Patient> patients = new ArrayList<>();
        Cursor cursor = database.rawQuery(selection, selectionArgs);
        try {
            while (cursor.moveToNext()) {
                Patient patient = new Patient(
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_PATIENT_ID)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_DEPARTMENT)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_DOCTOR_ID)),
                        cursor.getString(cursor.getColumnIndex(MedicalContract.PatientEntry.COLUMN_ROOM))
                );
                patients.add(patient);
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        Log.d(TAG, "Found patient by first Name");
        return patients;
    }

    public List<Test> getTestByPatientId(int id) {
        String selection = "SELECT * FROM test WHERE patientId = " + id;


        List<Test> tests = new ArrayList<>();
        Cursor cursor = database.rawQuery(selection, null);
        try {
            while (cursor.moveToNext()) {
                Test test = new Test(
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_TEST_ID)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_PATIENT_ID)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BPH)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BPL)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_TEMPERATURE)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BLOOD_PRESSURE))
                );
                tests.add(test);
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tests;
    }
    public List<Test> getTestByTestId(int id) {
        String selection = "SELECT * FROM test WHERE testId = " + id;


        List<Test> tests = new ArrayList<>();
        Cursor cursor = database.rawQuery(selection, null);
        try {
            while (cursor.moveToNext()) {
                Test test = new Test(
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_TEST_ID)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_PATIENT_ID)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BPH)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BPL)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_TEMPERATURE)),
                        cursor.getInt(cursor.getColumnIndex(MedicalContract.TestEntry.COLUMN_BLOOD_PRESSURE))
                );
                tests.add(test);
            }

        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tests;
    }

}

