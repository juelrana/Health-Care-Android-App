package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jamil.healthcareapp.Medicine.MedicineInfo;

import java.util.ArrayList;

/**
 * Created by md. tanvir on 26-Apr-16.
 */
public class MedicineController {

    private MedicineInfo medicineInfo;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public MedicineController(Context context) {
        helper = new DatabaseHelper(context);
    }


    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addMedicineInfo(MedicineInfo medicineInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, medicineInfo.getMid());
        contentValues.put(DatabaseHelper.COL_MEDICINE_NAME, medicineInfo.getMedicineName());
        contentValues.put(DatabaseHelper.COL_MEDICINE_DATE, medicineInfo.getDate());
        contentValues.put(DatabaseHelper.COL_MEDICINE_TIME, medicineInfo.getTime());
        contentValues.put(DatabaseHelper.COL_MEDICINE_DETAILS, medicineInfo.getDetails());
        contentValues.put(DatabaseHelper.COL_MEDICINE_ALARM, medicineInfo.getAlarm());
        contentValues.put(DatabaseHelper.COL_MEDICINE_REMINDER, medicineInfo.getReminder());
        long inserted = database.insert(DatabaseHelper.TABLE_MEDICINE_INFO, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<MedicineInfo> getAllMedicineInfo(int id) {

        this.open();
        ArrayList<MedicineInfo> medicineList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_MEDICINE_INFO, null, DatabaseHelper.COL_MID + "= " + id, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_NAME));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_TIME));
                String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_DETAILS));
                String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_REMINDER));
                String alarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_ALARM));

                medicineInfo = new MedicineInfo(did,mid,name,date,time,details,reminder,alarm);
                medicineList.add(medicineInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return medicineList;

    }

    public boolean deleteMedicineInfo(int id) {
        this.open();
        int deleted = database.delete(DatabaseHelper.TABLE_MEDICINE_INFO, DatabaseHelper.COL_MEDICINE_ID + "= " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }

    public MedicineInfo getMedicineInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_MEDICINE_INFO, new String[]{DatabaseHelper.COL_MEDICINE_ID, DatabaseHelper.COL_MID,
                        DatabaseHelper.COL_MEDICINE_NAME, DatabaseHelper.COL_MEDICINE_DATE, DatabaseHelper.COL_MEDICINE_TIME,
                        DatabaseHelper.COL_MEDICINE_DETAILS, DatabaseHelper.COL_MEDICINE_ALARM, DatabaseHelper.COL_MEDICINE_REMINDER},
                DatabaseHelper.COL_MEDICINE_ID + "= " + id, null, null, null, null);


        cursor.moveToFirst();


        int mdid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_ID));
        int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_NAME));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_DATE));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_TIME));
        String details = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_DETAILS));
        String reminder = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_REMINDER));
        String alarm = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEDICINE_ALARM));


        medicineInfo = new MedicineInfo(mdid,mid, name,date,time,details,alarm,reminder);
        this.close();

        return medicineInfo;
    }
    public boolean updateMedicineInfo(int id, MedicineInfo medicineInfo) {

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, medicineInfo.getMid());
        contentValues.put(DatabaseHelper.COL_MEDICINE_NAME, medicineInfo.getMedicineName());
        contentValues.put(DatabaseHelper.COL_MEDICINE_DATE, medicineInfo.getDate());
        contentValues.put(DatabaseHelper.COL_MEDICINE_TIME, medicineInfo.getTime());
        contentValues.put(DatabaseHelper.COL_MEDICINE_DETAILS, medicineInfo.getDetails());
        contentValues.put(DatabaseHelper.COL_MEDICINE_ALARM, medicineInfo.getAlarm());
        contentValues.put(DatabaseHelper.COL_MEDICINE_REMINDER, medicineInfo.getReminder());

        int updated = database.update(DatabaseHelper.TABLE_MEDICINE_INFO, contentValues, DatabaseHelper.COL_MEDICINE_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }


}
