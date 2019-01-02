package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jamil.healthcareapp.Vaccine.VaccineInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VaccineController {
    private  VaccineInfo vaccineInfo;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public VaccineController(Context context) {
        helper = new DatabaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }
    public boolean addVaccineInfo(VaccineInfo vaccineInfo) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, vaccineInfo.getMid());
        contentValues.put(DatabaseHelper.COL_VACCINE_NAME, vaccineInfo.getVaccineName());
        contentValues.put(DatabaseHelper.COL_VACCINE_DATE, vaccineInfo.getDate());
        contentValues.put(DatabaseHelper.COL_VACCINE_TIME, vaccineInfo.getTime());
        contentValues.put(DatabaseHelper.COL_VACCINE_DETAILS, vaccineInfo.getDetails());
        contentValues.put(DatabaseHelper.COL_VACCINE_REMINDER, vaccineInfo.getReminder());
        long inserted = database.insert(DatabaseHelper.TABLE_VACCINE_INFO, null, contentValues);
        this.close();
        if (inserted > 0) {
            return true;
        } else return false;
    }
    public ArrayList<VaccineInfo> getAllVaccineInfo(int id) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.open();
        ArrayList<VaccineInfo> vaccineList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_VACCINE_INFO, null, DatabaseHelper.COL_MID + "= ? AND " + DatabaseHelper.COL_VACCINE_DATE + ">=?", new String[]{String.valueOf(id), currentDate}, null, null, DatabaseHelper.COL_VACCINE_DATE);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int vid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_NAME));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_TIME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DETAILS));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_REMINDER));

                vaccineInfo = new VaccineInfo(vid,mid, name, date,time,details,reminder);
                vaccineList.add(vaccineInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return vaccineList;

    }

    public ArrayList<VaccineInfo> getAllPreviousVaccineInfo(int id) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.open();
        ArrayList<VaccineInfo> vaccineList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_VACCINE_INFO, null, DatabaseHelper.COL_MID +"= ? AND " +DatabaseHelper.COL_VACCINE_DATE+"<?", new String[]{String.valueOf(id),currentDate}, null, null, DatabaseHelper.COL_VACCINE_DATE);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int vid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_NAME));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_TIME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DETAILS));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_REMINDER));

                vaccineInfo = new VaccineInfo(vid,mid, name, date,time,details,reminder);
                vaccineList.add(vaccineInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return vaccineList;

    }

    public boolean deleteVaccineInfo(int id) {
        this.open();
        int deleted = database.delete(DatabaseHelper.TABLE_VACCINE_INFO, DatabaseHelper.COL_VACCINE_ID + "= " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }

    public VaccineInfo getVaccineInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_VACCINE_INFO, new String[]{DatabaseHelper.COL_VACCINE_ID, DatabaseHelper.COL_MID,
                        DatabaseHelper.COL_VACCINE_NAME, DatabaseHelper.COL_VACCINE_DATE, DatabaseHelper.COL_VACCINE_TIME,
                        DatabaseHelper.COL_VACCINE_DETAILS, DatabaseHelper.COL_VACCINE_REMINDER},
                DatabaseHelper.COL_VACCINE_ID + "= " + id, null, null, null, null);


        cursor.moveToFirst();

        int vid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_ID));
        int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_NAME));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DATE));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_TIME));
        String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_DETAILS));
        String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_VACCINE_REMINDER));


        vaccineInfo = new VaccineInfo(vid,mid, name,date,time,details,reminder);
        this.close();

        return vaccineInfo;
    }

    public boolean updateVaccineInfo(int id, VaccineInfo vaccineInfo) {

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, vaccineInfo.getMid());
        contentValues.put(DatabaseHelper.COL_VACCINE_NAME, vaccineInfo.getVaccineName());
        contentValues.put(DatabaseHelper.COL_VACCINE_TIME, vaccineInfo.getTime());
        contentValues.put(DatabaseHelper.COL_VACCINE_DATE, vaccineInfo.getDate());
        contentValues.put(DatabaseHelper.COL_VACCINE_DETAILS, vaccineInfo.getDetails());
        contentValues.put(DatabaseHelper.COL_VACCINE_REMINDER, vaccineInfo.getReminder());

        int updated = database.update(DatabaseHelper.TABLE_VACCINE_INFO, contentValues, DatabaseHelper.COL_VACCINE_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }


}
