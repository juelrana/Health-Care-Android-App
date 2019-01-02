package com.example.jamil.healthcareapp.Vaccine;

/**
 * Created by Jamil on 4/22/2016.
 */
public class VaccineInfo {
    private int id;
    private int mid;
    private String vaccineName;
    private String time;
    private String date;
    private String details;
    private String reminder;

    public VaccineInfo(int id, int mid, String vaccineName,String date,String time, String details, String reminder) {
        this.id = id;
        this.mid = mid;
        this.vaccineName = vaccineName;
        this.date = date;
        this.time = time;
        this.details = details;
        this.reminder = reminder;
    }

    public VaccineInfo(int mid, String vaccineName,String date,String time, String details, String reminder) {
        this.mid = mid;
        this.vaccineName = vaccineName;
        this.date = date;
        this.time = time;
        this.details = details;
        this.reminder = reminder;
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

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
