package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jamil.healthcareapp.Diet.DietInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jamil on 4/24/2016.
 */
public class DietController {
    private DatabaseHelper helper;
    private SQLiteDatabase database;
    private DietInfo dietInfo;

    public DietController (Context context) {
        helper = new DatabaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addDietInfo(DietInfo dietInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, dietInfo.getMid());
        contentValues.put(DatabaseHelper.COL_MEAL_NAME, dietInfo.getMealName());
        contentValues.put(DatabaseHelper.COL_MENU, dietInfo.getMenu());
        contentValues.put(DatabaseHelper.COL_DATE, dietInfo.getDate());
        contentValues.put(DatabaseHelper.COL_TIME, dietInfo.getTime());

        long inserted = database.insert(DatabaseHelper.TABLE_DIET_INFO, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }
    public ArrayList<DietInfo> getAllDietInfo(int id) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.open();
        ArrayList<DietInfo> dietList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_DIET_INFO, null, DatabaseHelper.COL_MID + "= ? AND " + DatabaseHelper.COL_DATE + "= ?", new String[]{String.valueOf(id), currentDate}, null, null, DatabaseHelper.COL_TIME);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DIET_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEAL_NAME));
                String menu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

                dietInfo = new DietInfo(did,mid, name, menu,date,time );
                dietList.add(dietInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return dietList;

    }

    public ArrayList<DietInfo> getAllDietInfoUpComingDate(int id) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.open();
        ArrayList<DietInfo> dietList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_DIET_INFO, null, DatabaseHelper.COL_MID +"= ? AND " +DatabaseHelper.COL_DATE+">?", new String[]{String.valueOf(id),currentDate}, null, null, DatabaseHelper.COL_DATE);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DIET_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEAL_NAME));
                String menu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

                dietInfo = new DietInfo(did,mid, name, menu,date,time );
                dietList.add(dietInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return dietList;

    }

    public ArrayList<DietInfo> getAllDietInfoPreviousDate(int id) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.open();
        ArrayList<DietInfo> dietList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_DIET_INFO, null, DatabaseHelper.COL_MID +"= ? AND " +DatabaseHelper.COL_DATE+"<?", new String[]{String.valueOf(id),currentDate}, null, null, DatabaseHelper.COL_DATE);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DIET_ID));
                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEAL_NAME));
                String menu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MENU));
                String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

                dietInfo = new DietInfo(did,mid, name, menu,date,time );
                dietList.add(dietInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return dietList;

    }


    public boolean deleteDietInfo(int id) {
        this.open();
        int deleted = database.delete(DatabaseHelper.TABLE_DIET_INFO, DatabaseHelper.COL_DIET_ID + "= " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }
    public DietInfo getDietInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_DIET_INFO, new String[]{DatabaseHelper.COL_DIET_ID, DatabaseHelper.COL_MID,
                        DatabaseHelper.COL_MEAL_NAME, DatabaseHelper.COL_MENU, DatabaseHelper.COL_DATE,
                        DatabaseHelper.COL_TIME},
                DatabaseHelper.COL_DIET_ID + "= " + id, null, null, null, null);


        cursor.moveToFirst();

        int did = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_DIET_ID));
        int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_MID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MEAL_NAME));
        String menu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MENU));
        String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DATE));
        String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TIME));

        dietInfo = new DietInfo(did,mid, name,menu,date,time);
        this.close();

        return dietInfo;
    }

    public boolean updateDietInfo(int id, DietInfo dietInfo) {

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_MID, dietInfo.getMid());
        contentValues.put(DatabaseHelper.COL_MEAL_NAME, dietInfo.getMealName());
        contentValues.put(DatabaseHelper.COL_MENU, dietInfo.getMenu());
        contentValues.put(DatabaseHelper.COL_DATE, dietInfo.getDate());
        contentValues.put(DatabaseHelper.COL_TIME, dietInfo.getTime());

        int updated = database.update(DatabaseHelper.TABLE_DIET_INFO, contentValues, DatabaseHelper.COL_DIET_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }



}
