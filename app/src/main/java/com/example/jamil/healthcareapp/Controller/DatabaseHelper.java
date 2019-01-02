package com.example.jamil.healthcareapp.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;import java.lang.Override;import java.lang.String;

public class DatabaseHelper extends SQLiteOpenHelper {

    static  final int DATABASE_VERSION=1;
    static  final  String DATABASE_NAME="healthCare.db";

    static  final String TABLE_MEMBER_INFO="member_info";
    static final String COL_ID="id";
    static final String COL_NAME="name";
    static final String COL_AGE="age";
    static final String COL_HEIGHT="height";
    static final String COL_WEIGHT="weight";
    static final String COL_BLOOD_GROUP="blood_group";
    static final String COL_RELATION="relation";
    static final String COL_MAJOR_DISEASES ="major_diseases";


    String CREATE_TABLE_MEMBER_INFO=" CREATE TABLE " + TABLE_MEMBER_INFO +
            " ( " + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT," +COL_AGE + " TEXT," +COL_HEIGHT + " TEXT," +
            COL_WEIGHT + " TEXT," +COL_BLOOD_GROUP + " TEXT,"+COL_RELATION + " TEXT," + COL_MAJOR_DISEASES+ " TEXT )";


    static  final String TABLE_DIET_INFO="diet_info";
    static final String COL_DIET_ID="id";
    static final String COL_MID="mid";
    static final String COL_MEAL_NAME="mealName";
    static final String COL_MENU="menu";
    static final String COL_DATE="date";
    static final String COL_TIME="time";

    String CREATE_TABLE_DIET_INFO=" CREATE TABLE " + TABLE_DIET_INFO +
            " ( " + COL_DIET_ID + " INTEGER PRIMARY KEY," + COL_MID + " INTEGER," +COL_MEAL_NAME + " TEXT," +COL_MENU + " TEXT," +
            COL_DATE + " TEXT," +COL_TIME + " TEXT)";


    static  final String TABLE_DOCTOR_INFO="doctor_info";
    static final String COL_DOCTOR_ID="id";
    static final String COL_DOCTOR_NAME="doctorName";
    static final String COL_DOCTOR_TYPE="doctorType";
    static final String COL_APPOINTMENT_DATE="appointmentDate";
    static final String COL_APPOINTMENT_TIME="appointmentTime";
    static final String COL_MOBILE_NO="mobileNo";
    static final String COL_EMAIL_NO="emailNo";
    static final String COL_DETAILS="details";

    String CREATE_TABLE_DOCTOR_INFO=" CREATE TABLE " + TABLE_DOCTOR_INFO +
            " ( " + COL_DOCTOR_ID + " INTEGER PRIMARY KEY," + COL_MID + " INTEGER," +COL_DOCTOR_NAME + " TEXT," +COL_DOCTOR_TYPE + " TEXT," +
            COL_APPOINTMENT_DATE + " TEXT,"+COL_APPOINTMENT_TIME + " TEXT,"+ COL_MOBILE_NO + " TEXT," +COL_EMAIL_NO + " TEXT,"+COL_DETAILS + " TEXT)";


    static  final String TABLE_VACCINE_INFO="vaccine_info";
    static final String COL_VACCINE_ID="id";
    static final String COL_VACCINE_NAME="vaccineName";
    static final String COL_VACCINE_DATE="date";
    static final String COL_VACCINE_TIME="time";
    static final String COL_VACCINE_DETAILS="details";
    static final String COL_VACCINE_REMINDER="reminder";

    String CREATE_TABLE_VACCINE_INFO=" CREATE TABLE " + TABLE_VACCINE_INFO +
            " ( " + COL_VACCINE_ID + " INTEGER PRIMARY KEY," + COL_MID + " INTEGER," +COL_VACCINE_NAME + " TEXT," +
            COL_VACCINE_DATE + " TEXT," +COL_VACCINE_TIME + " TEXT," +COL_VACCINE_DETAILS + " TEXT,"+COL_VACCINE_REMINDER + " TEXT)";


    static final String TABLE_MEDICINE_INFO="medicine_info";
    static final String COL_MEDICINE_ID="id";
    static final String COL_MEDICINE_NAME="medicineName";
    static final String COL_MEDICINE_DATE="date";
    static final String COL_MEDICINE_TIME="time";
    static final String COL_MEDICINE_DETAILS="details";
    static final String COL_MEDICINE_ALARM="alarm";
    static final String COL_MEDICINE_REMINDER="reminder";

    String CREATE_TABLE_MEDICINE_INFO=" CREATE TABLE " + TABLE_MEDICINE_INFO +
            " ( " + COL_MEDICINE_ID + " INTEGER PRIMARY KEY," + COL_MID + " INTEGER," +COL_MEDICINE_NAME + " TEXT," +COL_MEDICINE_DATE + " TEXT," +
            COL_MEDICINE_TIME + " TEXT," +COL_MEDICINE_DETAILS + " TEXT," +COL_MEDICINE_ALARM + " TEXT,"+COL_MEDICINE_REMINDER+ " TEXT)";

    static final String TABLE_HOSPITAL_INFO="hospital_info";
    static final String COL_HOSPITAL_ID="id";
    static final String COL_HOSPITAL_NAME="name";
    static final String COL_HOSPITAL_PHONE="phone";
    static final String COL_HOSPITAL_EMAIL="email";
    static final String COL_HOSPITAL_ADDRESS="address";


    String CREATE_TABLE_HOSPITAL_INFO=" CREATE TABLE " + TABLE_HOSPITAL_INFO +
            " ( " + COL_HOSPITAL_ID + " INTEGER PRIMARY KEY," + COL_HOSPITAL_NAME + " TEXT," +COL_HOSPITAL_PHONE + " TEXT," +
            COL_HOSPITAL_EMAIL + " TEXT," +COL_HOSPITAL_ADDRESS + " TEXT)";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEMBER_INFO);
        db.execSQL(CREATE_TABLE_DIET_INFO);
        db.execSQL(CREATE_TABLE_DOCTOR_INFO);
        db.execSQL(CREATE_TABLE_VACCINE_INFO);
        db.execSQL(CREATE_TABLE_MEDICINE_INFO);
        db.execSQL(CREATE_TABLE_HOSPITAL_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
///
    }
}
