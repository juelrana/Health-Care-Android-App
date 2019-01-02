package com.example.jamil.healthcareapp.Diet;

/**
 * Created by software0 on 4/20/2016.
 */
public class DietInfo {
    private int id;
    private int mid;
    private String mealName;
    private String menu;
    private String date;
    private String time;

    public DietInfo(int id, int mid, String mealName, String menu, String date, String time) {
        setId(id);
        setMid(mid);
        setMealName(mealName);
        setMenu(menu);
        setDate(date);
        setTime(time);
    }

    public DietInfo(int mid, String mealName, String menu, String date, String time) {
        setMid(mid);
        setMealName(mealName);
        setMenu(menu);
        setDate(date);
        setTime(time);
    }

    public DietInfo() {

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

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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
}
