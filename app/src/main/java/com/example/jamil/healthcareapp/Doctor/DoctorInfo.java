package com.example.jamil.healthcareapp.Doctor;

/**
 * Created by Jamil on 4/22/2016.
 */
public class DoctorInfo {
    private int id;
    private int mid;
    private String doctorName;
    private String doctorType;
    private String appointmentDate;
    private String appointmentTime;
    private String mobileNo;
    private String emailNo;
    private String details;

    public DoctorInfo(int id, int mid, String doctorName, String doctorType, String appointmentDate,String appointmentTime,String mobileNo, String emailNo, String details) {
        this.id = id;
        this.mid = mid;
        this.doctorName = doctorName;
        this.doctorType = doctorType;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.mobileNo = mobileNo;
        this.emailNo = emailNo;
        this.details = details;
    }

    public DoctorInfo(int mid, String doctorName, String doctorType,String appointmentDate,String appointmentTime, String mobileNo, String emailNo, String details) {
        this.mid = mid;
        this.doctorName = doctorName;
        this.doctorType = doctorType;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.mobileNo = mobileNo;
        this.emailNo = emailNo;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(String emailNo) {
        this.emailNo = emailNo;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

}
