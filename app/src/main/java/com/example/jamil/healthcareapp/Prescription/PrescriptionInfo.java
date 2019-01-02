package com.example.jamil.healthcareapp.Prescription;

/**
 * Created by software0 on 4/28/2016.
 */
public class PrescriptionInfo {
    private int id;
    private int mid;
    private int did;
    private  String image;
    private String hospitalName;
    private  String date;
    private  String details;

    public PrescriptionInfo(int id, int mid, int did, String image, String hospitalName, String date, String details) {
        this.id = id;
        this.mid = mid;
        this.did = did;
        this.image = image;
        this.hospitalName = hospitalName;
        this.date = date;
        this.details = details;
    }

    public PrescriptionInfo(int mid, int did, String image, String hospitalName, String date, String details) {
        this.mid = mid;
        this.did = did;
        this.image = image;
        this.hospitalName = hospitalName;
        this.date = date;
        this.details = details;
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

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
