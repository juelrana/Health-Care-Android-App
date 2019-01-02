package com.example.jamil.healthcareapp.Vaccine;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.VaccineController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddVaccineActivity extends AppCompatActivity implements View.OnClickListener {

    int mid;
    int vid;
    EditText vaccineNameEt;
    EditText timeEt;
    EditText dateEt;
    EditText detailsEt;
    CheckBox reminderCheckBox;
    private VaccineInfo vaccineInfo;
    private VaccineController vaccineController;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);


        mid = getIntent().getIntExtra("mid", 0);
        vid = getIntent().getIntExtra("vid", 0);
        vaccineController = new VaccineController(this);
        vaccineNameEt = (EditText) findViewById(R.id.vaccineNameEditText);
        dateEt = (EditText) findViewById(R.id.vaccineDateEditText);
        timeEt = (EditText) findViewById(R.id.vaccineTimeEditText);

        detailsEt = (EditText) findViewById(R.id.vaccineDetailsEditText);
        reminderCheckBox = (CheckBox) findViewById(R.id.reminderCheckBox);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateEt.setInputType(InputType.TYPE_NULL);
        dateEt.setOnClickListener(this);
        timeEt.setInputType(InputType.TYPE_NULL);
        timeEt.setOnClickListener(this);

        if (vid != 0) {
            vaccineNameEt.setText(vaccineController.getVaccineInfo(vid).getVaccineName().toString());
            dateEt.setText(vaccineController.getVaccineInfo(vid).getDate().toString());
            timeEt.setText(vaccineController.getVaccineInfo(vid).getTime().toString());
            detailsEt.setText(vaccineController.getVaccineInfo(vid).getDetails().toString());
            setTitle("Update Form");

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_vaccine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(AddVaccineActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_save) {

            if (vid != 0) {

                if (vaccineNameEt.getText().toString().isEmpty() || dateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Name and Date", Toast.LENGTH_LONG).show();
                } else {
                    vaccineInfo = new VaccineInfo(mid, vaccineNameEt.getText().toString(), dateEt.getText().toString(),
                            timeEt.getText().toString(), detailsEt.getText().toString(), null);

                    boolean updated = vaccineController.updateVaccineInfo(vid, vaccineInfo);
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddVaccineActivity.this, VaccineInfoActivity.class);
                        intent.putExtra("mid", mid);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                if (vaccineNameEt.getText().toString().isEmpty() || dateEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Name and Date", Toast.LENGTH_LONG).show();
                } else {

                    vaccineInfo = new VaccineInfo(mid, vaccineNameEt.getText().toString(), dateEt.getText().toString(),
                            timeEt.getText().toString(), detailsEt.getText().toString(), null);
                    boolean inserted = vaccineController.addVaccineInfo(vaccineInfo);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                    }

                }
            }
            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(AddVaccineActivity.this, VaccineInfoActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vaccineDateEditText: {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                dateEt.setText(dateFormatter.format(newDate.getTime()));
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
            break;

            case R.id.vaccineTimeEditText: {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                timeEt.setText(hourOfDay + ":" + minute);
                            }
                        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddVaccineActivity.this, VaccineInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }
}
