package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jamil.healthcareapp.Doctor.DoctorInfo;

import java.util.ArrayList;


public class DoctorController {
    private DoctorInfo doctorInfo;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public DoctorController(Context context) {
        helper = new DatabaseHelper(context);
    }


    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addDoctorInfo(DoctorInfo doctorInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, doctorInfo.getMid());
        contentValues.put(DatabaseHelper.COL_DOCTOR_NAME, doctorInfo.getDoctorName());
        contentValues.put(DatabaseHelper.COL_DOCTOR_TYPE, doctorInfo.getDoctorType());
        contentValues.put(DatabaseHelper.COL_APPOINTMENT_DATE, doctorInfo.getAppointmentDate());
        contentValues.put(DatabaseHelper.COL_APPOINTMENT_TIME, doctorInfo.getAppointmentTime());
        contentValues.put(DatabaseHelper.COL_MOBILE_NO, doctorInfo.getMobileNo());
        contentValues.put(DatabaseHelper.COL_EMAIL_NO, doctorInfo.getEmailNo());
        contentValues.put(DatabaseHelper.COL_DETAILS, doctorInfo.getDetails());
        long inserted = database.insert(DatabaseHelper.TABLE_DOCTOR_INFO, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<DoctorInfo> getAllDoctorInfo(int id) {

        this.open();
        ArrayList<DoctorInfo> doctorList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_DOCTOR_INFO, null, DatabaseHelper.COL_MID + "= " + id, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_NAME));
                String doctorType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_TYPE));
                String appointmentDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT_DATE));
                String appointmentTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT_TIME));
                String mobileNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOBILE_NO));
                String emailNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL_NO));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));

                doctorInfo = new DoctorInfo(did,mid, name, doctorType,appointmentDate,appointmentTime,mobileNo,emailNo,details );
                doctorList.add(doctorInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return doctorList;

    }

       public boolean deleteDoctorInfo(int id) {
        this.open();
        int deleted = database.delete(DatabaseHelper.TABLE_DOCTOR_INFO, DatabaseHelper.COL_DOCTOR_ID + "= " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }

    public DoctorInfo getDoctorInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_DOCTOR_INFO, new String[]{DatabaseHelper.COL_DOCTOR_ID, DatabaseHelper.COL_MID,
                        DatabaseHelper.COL_DOCTOR_NAME, DatabaseHelper.COL_DOCTOR_TYPE, DatabaseHelper.COL_APPOINTMENT_DATE,
                        DatabaseHelper.COL_APPOINTMENT_TIME, DatabaseHelper.COL_MOBILE_NO, DatabaseHelper.COL_EMAIL_NO, DatabaseHelper.COL_DETAILS},
                DatabaseHelper.COL_DOCTOR_ID + "= " + id, null, null, null, null);


        cursor.moveToFirst();

        int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_ID));
        int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_NAME));
        String doctorType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DOCTOR_TYPE));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT_DATE));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_APPOINTMENT_TIME));
        String mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MOBILE_NO));
        String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL_NO));
        String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DETAILS));

        doctorInfo = new DoctorInfo(did,mid, name,doctorType,date,time,mobile,email,details);
        this.close();

        return doctorInfo;
    }
    public boolean updateDoctorInfo(int id, DoctorInfo doctorInfo) {

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, doctorInfo.getMid());
        contentValues.put(DatabaseHelper.COL_DOCTOR_NAME, doctorInfo.getDoctorName());
        contentValues.put(DatabaseHelper.COL_DOCTOR_TYPE, doctorInfo.getDoctorType());
        contentValues.put(DatabaseHelper.COL_APPOINTMENT_DATE, doctorInfo.getAppointmentDate());
        contentValues.put(DatabaseHelper.COL_APPOINTMENT_TIME, doctorInfo.getAppointmentTime());
        contentValues.put(DatabaseHelper.COL_MOBILE_NO, doctorInfo.getMobileNo());
        contentValues.put(DatabaseHelper.COL_EMAIL_NO, doctorInfo.getEmailNo());
        contentValues.put(DatabaseHelper.COL_DETAILS, doctorInfo.getDetails());


        int updated = database.update(DatabaseHelper.TABLE_DOCTOR_INFO, contentValues, DatabaseHelper.COL_DOCTOR_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }

}
