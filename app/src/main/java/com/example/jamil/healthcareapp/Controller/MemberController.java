package com.example.jamil.healthcareapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Member.MemberInfo;

import java.util.ArrayList;

/**
 * Created by Jamil on 4/19/2016.
 */
public class MemberController {
    private MemberInfo memberInfo;
    private DatabaseHelper helper;
    private SQLiteDatabase database;

    public MemberController(Context context) {
        helper = new DatabaseHelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addMemberInfo(MemberInfo memberInfo) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME, memberInfo.getName());
        contentValues.put(DatabaseHelper.COL_AGE, memberInfo.getAge());
        contentValues.put(DatabaseHelper.COL_HEIGHT, memberInfo.getHeight());
        contentValues.put(DatabaseHelper.COL_WEIGHT, memberInfo.getWeight());
        contentValues.put(DatabaseHelper.COL_BLOOD_GROUP, memberInfo.getBloodGroup());
        contentValues.put(DatabaseHelper.COL_RELATION, memberInfo.getRelation());
        contentValues.put(DatabaseHelper.COL_MAJOR_DISEASES, memberInfo.getMajorDiseases());

        long inserted = database.insert(DatabaseHelper.TABLE_MEMBER_INFO, null, contentValues);
        this.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<MemberInfo> getAllMemberInfo() {

        this.open();
        ArrayList<MemberInfo> memberInfoList = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_MEMBER_INFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AGE));
                String height = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HEIGHT));
                String weight = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_WEIGHT));
                String bloodGroup = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BLOOD_GROUP));
                String relation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_RELATION));
                String majorDiseases = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MAJOR_DISEASES));

                memberInfo = new MemberInfo(mid, name, age, height, weight, bloodGroup, relation, majorDiseases);
                memberInfoList.add(memberInfo);
                cursor.moveToNext();
            }
        }
        this.close();
        return memberInfoList;

    }

    public boolean deleteMember(int id) {
        this.open();
        int deleted = database.delete(DatabaseHelper.TABLE_MEMBER_INFO, DatabaseHelper.COL_ID + "= " + id, null);
        database.delete(DatabaseHelper.TABLE_DIET_INFO, DatabaseHelper.COL_MID + "= " + id, null);
        database.delete(DatabaseHelper.TABLE_DOCTOR_INFO, DatabaseHelper.COL_MID + "= " + id, null);
        database.delete(DatabaseHelper.TABLE_VACCINE_INFO, DatabaseHelper.COL_MID + "= " + id, null);
        database.delete(DatabaseHelper.TABLE_MEDICINE_INFO, DatabaseHelper.COL_MID + "= " + id, null);

        this.close();
        if (deleted > 0) {
            return true;
        } else return false;

    }


    public MemberInfo getMemberInfo(int id) {

        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_MEMBER_INFO, new String[]{DatabaseHelper.COL_ID, DatabaseHelper.COL_NAME,
                        DatabaseHelper.COL_AGE, DatabaseHelper.COL_HEIGHT, DatabaseHelper.COL_WEIGHT, DatabaseHelper.COL_BLOOD_GROUP, DatabaseHelper.COL_RELATION, DatabaseHelper.COL_MAJOR_DISEASES},
                DatabaseHelper.COL_ID + "= " + id, null, null, null, null);


        cursor.moveToFirst();

        int mid = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String age = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AGE));
        String height = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HEIGHT));
        String weight = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_WEIGHT));
        String bloodGroup = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BLOOD_GROUP));
        String relation = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_RELATION));
        String majorDiseases = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_MAJOR_DISEASES));

        memberInfo = new MemberInfo(mid, name, age, height, weight, bloodGroup, relation,majorDiseases);
        this.close();

        return memberInfo;
    }

    public boolean updateMemberInfo(int id, MemberInfo memberInfo) {

        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME, memberInfo.getName());
        contentValues.put(DatabaseHelper.COL_AGE, memberInfo.getAge());
        contentValues.put(DatabaseHelper.COL_HEIGHT, memberInfo.getHeight());
        contentValues.put(DatabaseHelper.COL_WEIGHT, memberInfo.getWeight());
        contentValues.put(DatabaseHelper.COL_BLOOD_GROUP, memberInfo.getBloodGroup());
        contentValues.put(DatabaseHelper.COL_RELATION, memberInfo.getRelation());
        contentValues.put(DatabaseHelper.COL_MAJOR_DISEASES, memberInfo.getMajorDiseases());


        int updated = database.update(DatabaseHelper.TABLE_MEMBER_INFO, contentValues, DatabaseHelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }


}
