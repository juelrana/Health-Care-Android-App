package com.example.jamil.healthcareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Diet.DietActivity;
import com.example.jamil.healthcareapp.Doctor.DoctorInfoActivity;
import com.example.jamil.healthcareapp.Medicine.MedicineInfoActivity;
import com.example.jamil.healthcareapp.Prescription.PrescriptionActivity;
import com.example.jamil.healthcareapp.Vaccine.VaccineInfoActivity;

public class PersonalActivity extends AppCompatActivity {
    int mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        mid = getIntent().getIntExtra("mid", 0);
    }

    public void dietOption(View view) {
        Intent intent = new Intent(PersonalActivity.this, DietActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

    public void doctorOption(View view) {
        Intent intent = new Intent(PersonalActivity.this, DoctorInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

    public void vaccineOption(View view) {
        Intent intent = new Intent(PersonalActivity.this, VaccineInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }
    public void medicineOption(View view) {
        Intent intent = new Intent(PersonalActivity.this, MedicineInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
        startActivity(intent);
    }


}