package com.example.jamil.healthcareapp.Member;

/**
 * Created by Jamil on 4/19/2016.
 */
public class MemberInfo {
    private int id;
    private String name;
    private String age;
    private String height;
    private String weight;
    private String bloodGroup;
    private String relation;
    private String majorDiseases;

    public MemberInfo(int id, String name, String age, String height, String weight, String bloodGroup, String relation, String majorDiseases) {
        setId(id);
        setName(name);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setBloodGroup(bloodGroup);
        setRelation(relation);
        setMajorDiseases(majorDiseases);
    }

    public MemberInfo(String name, String age, String height, String weight, String bloodGroup, String relation, String majorDiseases) {
        setName(name);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setBloodGroup(bloodGroup);
        setRelation(relation);
        setMajorDiseases(majorDiseases);
    }

    public MemberInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMajorDiseases() {
        return majorDiseases;
    }

    public void setMajorDiseases(String majorDiseases) {
        this.majorDiseases = majorDiseases;
    }
}
