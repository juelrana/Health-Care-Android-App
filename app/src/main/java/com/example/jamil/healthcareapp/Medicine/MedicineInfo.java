package com.example.jamil.healthcareapp.Medicine;

/**
 * Created by md. tanvir on 24-Apr-16.
 */
public class MedicineInfo {

    private int id;
    private int mid;
    private String medicineName;
    private String date;
    private String time;
    private String details;
    private String alarm;
    private String reminder;


    public MedicineInfo(int id, int mid, String medicineName, String date, String time,String details, String alarm, String reminder) {
        this.id = id;
        this.mid = mid;
        this.medicineName = medicineName;
        this.date = date;
        this.time = time;
        this.details=details;
        this.reminder = reminder;
        this.alarm = alarm;
    }

    public MedicineInfo(int mid, String medicineName, String date, String time,String details, String alarm, String reminder) {
        this.mid = mid;
        this.medicineName = medicineName;
        this.date = date;
        this.time = time;
        this.details=details;
        this.reminder = reminder;
        this.alarm = alarm;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
