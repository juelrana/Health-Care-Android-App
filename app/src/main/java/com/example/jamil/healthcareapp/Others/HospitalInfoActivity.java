package com.example.jamil.healthcareapp.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jamil.healthcareapp.Controller.DoctorController;
import com.example.jamil.healthcareapp.Controller.HospitalController;
import com.example.jamil.healthcareapp.Doctor.DoctorAdapter;
import com.example.jamil.healthcareapp.Doctor.DoctorInfo;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

public class HospitalInfoActivity extends AppCompatActivity {
ListView listView;
    private HospitalAdapter adapter;
    private HospitalController hospitalController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);
        listView= (ListView) findViewById(R.id.hospitalListView);

        hospitalController = new HospitalController(this);
        final ArrayList<HospitalInfo> hospitalList = hospitalController.getAllHospitalInfo();
        adapter = new HospitalAdapter(this, hospitalList);
        listView.setAdapter(adapter);

    }
}
