package com.gb.comp3074_assignment2.db;

import android.provider.BaseColumns;

import junit.framework.Test;

/**
 * Created by Dylan on 2017-11-23.
 */

public final class MedicalContract {

    static final String CREATE_PATIENT_ENTRY_TABLE =
            "CREATE TABLE " + PatientEntry.TABLE_NAME +
                    " ( " +
                    PatientEntry.COLUMN_PATIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                    PatientEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    PatientEntry.COLUMN_LAST_NAME + " TEXT NOT NULL , " +
                    PatientEntry.COLUMN_DEPARTMENT + " TEXT NOT NULL, " +
                    PatientEntry.COLUMN_DOCTOR_ID + " INTEGER NOT NULL, " +
                    PatientEntry.COLUMN_ROOM + " TEXT NOT NULL, " +
                    "UNIQUE ( " + PatientEntry.COLUMN_PATIENT_ID + ") ON CONFLICT REPLACE )";
    static final String CREATE_TEST_ENTRY_TABLE =
            "CREATE TABLE " + TestEntry.TABLE_NAME +
                    " ( " +
                    TestEntry.COLUMN_TEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TestEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL, " +
                    TestEntry.COLUMN_BPL + " TEXT NOT NULL, " +
                    TestEntry.COLUMN_BPH + " TEXT NOT NULL, " +
                    TestEntry.COLUMN_TEMPERATURE + " TEXT NOT NULL, " +
                    "UNIQUE ( " + TestEntry.COLUMN_TEST_ID + ") ON CONFLICT REPLACE )";
    static final String CREATE_NURSE_ENTRY_TABLE =
            "CREATE TABLE " + NurseEntry.TABLE_NAME +
                    " ( " +
                    NurseEntry.COLUMN_NURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NurseEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    NurseEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    NurseEntry.COLUMN_DEPARTMENT + " TEXT NOT NULL, " +
                    "UNIQUE ( " + NurseEntry.COLUMN_NURSE_ID + ") ON CONFLICT REPLACE )";
    static final String CREATE_DOCTOR_ENTRY_TABLE =
            "CREATE TABLE " + DoctorEntry.TABLE_NAME +
                    " ( " +
                    DoctorEntry.COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DoctorEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    DoctorEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    DoctorEntry.COLUMN_DEPARTMENT + " TEXT NOT NULL, " +
                    "UNIQUE ( " + DoctorEntry.COLUMN_DOCTOR_ID + ") ON CONFLICT REPLACE )";

    private MedicalContract() {
    }

    public static class PatientEntry implements BaseColumns {
        public static final String TABLE_NAME = "patient";
        public static final String COLUMN_PATIENT_ID = "patientId";
        public static final String COLUMN_FIRST_NAME = "firstname";
        public static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_DEPARTMENT = "department";
        public static final String COLUMN_DOCTOR_ID = "doctorId";
        public static final String COLUMN_ROOM = "room";
    }

    public static class TestEntry implements BaseColumns {
        public static final String TABLE_NAME = "test";
        public static final String COLUMN_TEST_ID = "testId";
        public static final String COLUMN_PATIENT_ID = "patientId";
        public static final String COLUMN_BPL = "BPL";
        public static final String COLUMN_BPH = "BPH";
        public static final String COLUMN_TEMPERATURE = "temperature";
    }

    public static class NurseEntry implements BaseColumns {
        public static final String TABLE_NAME = "nurse";
        public static final String COLUMN_NURSE_ID = "nurseId";
        public static final String COLUMN_FIRST_NAME = "firstname";
        public static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_DEPARTMENT = "department";
    }

    public static class DoctorEntry implements BaseColumns {
        public static final String TABLE_NAME = "doctor";
        public static final String COLUMN_DOCTOR_ID = "doctorId";
        public static final String COLUMN_FIRST_NAME = "firstname";
        public static final String COLUMN_LAST_NAME = "lastname";
        public static final String COLUMN_DEPARTMENT = "department";
    }
}

