package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jamil.healthcareapp.Diet.DietInfo;
import com.example.jamil.healthcareapp.Others.HospitalInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jamil on 4/30/2016.
 */
public class HospitalController {
    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private HospitalInfo hospitalInfo;

    public HospitalController (Context context) {
        helper = new DatabaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }
    public boolean addHospitalInfo(HospitalInfo hospitalInfo) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_HOSPITAL_NAME, hospitalInfo.getName());
        contentValues.put(DatabaseHelper.COL_HOSPITAL_PHONE, hospitalInfo.getPhone());
        contentValues.put(DatabaseHelper.COL_HOSPITAL_EMAIL, hospitalInfo.getEmail());
        contentValues.put(DatabaseHelper.COL_HOSPITAL_ADDRESS, hospitalInfo.getAddress());

        long inserted = database.insert(DatabaseHelper.TABLE_HOSPITAL_INFO, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<HospitalInfo> getAllHospitalInfo() {
        this.open();
        ArrayList<HospitalInfo> hopitalList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_HOSPITAL_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_HOSPITAL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOSPITAL_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOSPITAL_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOSPITAL_EMAIL));
                String address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOSPITAL_ADDRESS));

                hospitalInfo = new HospitalInfo(id,name,phone,email,address);
                hopitalList.add(hospitalInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return hopitalList;

    }



}
