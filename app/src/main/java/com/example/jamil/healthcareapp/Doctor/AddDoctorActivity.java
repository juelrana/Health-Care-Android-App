package com.example.jamil.healthcareapp.Doctor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.DoctorController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDoctorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText doctorNameEt;
    Spinner specializationEt;
    EditText appointmentDateEt;
    EditText appointmentTimeEt;
    EditText mobileEt;
    EditText emailEt;
    EditText detailsEt;
    private DoctorInfo doctorInfo;
    private DoctorController doctorController;
    private SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int mid;
    int did;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        doctorController = new DoctorController(this);

        doctorNameEt = (EditText) findViewById(R.id.doctorNameEditText);
        specializationEt = (Spinner) findViewById(R.id.specializationDropDownList);
        specializationEt.requestFocus();

        appointmentDateEt = (EditText) findViewById(R.id.dateEditText);
        appointmentTimeEt = (EditText) findViewById(R.id.timeEditText);

        mobileEt = (EditText) findViewById(R.id.mobileEditText);
        emailEt = (EditText) findViewById(R.id.emailEditText);
        detailsEt = (EditText) findViewById(R.id.detailsEditText);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        appointmentDateEt.setInputType(InputType.TYPE_NULL);
        appointmentDateEt.setOnClickListener(this);

        appointmentTimeEt.setInputType(InputType.TYPE_NULL);
        appointmentTimeEt.setOnClickListener(this);

        mid = getIntent().getIntExtra("mid", 0);
        did = getIntent().getIntExtra("did", 0);
        if (did != 0) {
            doctorNameEt.setText(doctorController.getDoctorInfo(did).getDoctorName().toString());
            //specializationEt.setText(doctorController.getDoctorInfo(mid).getDoctorName().toString());
            appointmentDateEt.setText(doctorController.getDoctorInfo(did).getAppointmentDate().toString());
            appointmentTimeEt.setText(doctorController.getDoctorInfo(did).getAppointmentTime().toString());
            mobileEt.setText(doctorController.getDoctorInfo(did).getMobileNo().toString());
            emailEt.setText(doctorController.getDoctorInfo(did).getEmailNo().toString());
            detailsEt.setText(doctorController.getDoctorInfo(did).getDetails().toString());
            setTitle("Update Form");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_doctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(AddDoctorActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_save) {
            if (did != 0) {
                if (doctorNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(AddDoctorActivity.this, "Enter name ", Toast.LENGTH_SHORT).show();

                } else {
                    doctorInfo = new DoctorInfo(mid, doctorNameEt.getText().toString(), specializationEt.getSelectedItem().toString(),
                            appointmentDateEt.getText().toString(), appointmentTimeEt.getText().toString(), mobileEt.getText().toString(), emailEt.getText().toString(), detailsEt.getText().toString());

                    boolean updated = doctorController.updateDoctorInfo(did, doctorInfo);
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddDoctorActivity.this, DoctorInfoActivity.class);
                        intent.putExtra("mid", mid);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                    }
                }

            } else {
                if (doctorNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(AddDoctorActivity.this, "Enter name ", Toast.LENGTH_SHORT).show();

                } else {
                    doctorInfo = new DoctorInfo(mid, doctorNameEt.getText().toString(), specializationEt.getSelectedItem().toString(),
                            appointmentDateEt.getText().toString(), appointmentTimeEt.getText().toString(), mobileEt.getText().toString(), emailEt.getText().toString(), detailsEt.getText().toString());
                    boolean inserted = doctorController.addDoctorInfo(doctorInfo);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                        doctorNameEt.getText().clear();
                        appointmentDateEt.getText().clear();
                        appointmentTimeEt.getText().clear();
                        mobileEt.getText().clear();
                        emailEt.getText().clear();
                        detailsEt.getText().clear();

                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }

            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(AddDoctorActivity.this, DoctorInfoActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dateEditText: {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                appointmentDateEt.setText(dateFormatter.format(newDate.getTime()));
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            }
            case R.id.timeEditText: {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                appointmentTimeEt.setText(hourOfDay + ":" + minute);
                            }
                        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddDoctorActivity.this, DoctorInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

}
