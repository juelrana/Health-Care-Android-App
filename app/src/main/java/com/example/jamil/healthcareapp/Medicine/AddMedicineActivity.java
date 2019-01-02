package com.example.jamil.healthcareapp.Medicine;

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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.MedicineController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;
import com.example.jamil.healthcareapp.Vaccine.VaccineInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMedicineActivity extends AppCompatActivity implements View.OnClickListener {

    int mid;
    int mdid;
    EditText medicineNameEt;
    EditText dateEt;
    EditText timeEt;
    EditText detailsEt;
    private MedicineController medicineController;
    private MedicineInfo medicineInfo;
    private SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        medicineController = new MedicineController(this);
        mid = getIntent().getIntExtra("mid", 0);
        mdid = getIntent().getIntExtra("mdid", 0);

        medicineNameEt = (EditText) findViewById(R.id.medicineNameEditText);
        dateEt = (EditText) findViewById(R.id.medicineDateEditText);
        timeEt = (EditText) findViewById(R.id.medicineTimeEditText);
        detailsEt = (EditText) findViewById(R.id.medicineDetailsEditText);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateEt.setInputType(InputType.TYPE_NULL);
        dateEt.setOnClickListener(this);

        timeEt.setInputType(InputType.TYPE_NULL);
        timeEt.setOnClickListener(this);

        if (mdid != 0) {
            medicineNameEt.setText(medicineController.getMedicineInfo(mdid).getMedicineName().toString());
            dateEt.setText(medicineController.getMedicineInfo(mdid).getDate().toString());
            timeEt.setText(medicineController.getMedicineInfo(mdid).getTime().toString());
            detailsEt.setText(medicineController.getMedicineInfo(mdid).getDetails().toString());
            setTitle("Update Form");

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(AddMedicineActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_save) {
            if (mdid != 0) {
                if (medicineNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(AddMedicineActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else {
                    medicineInfo = new MedicineInfo(mid, medicineNameEt.getText().toString(),
                            dateEt.getText().toString(), timeEt.getText().toString(), detailsEt.getText().toString(), null, null);


                    boolean updated = medicineController.updateMedicineInfo(mdid, medicineInfo);
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddMedicineActivity.this, MedicineInfoActivity.class);
                        intent.putExtra("mid", mid);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                if (medicineNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(AddMedicineActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else {
                    medicineInfo = new MedicineInfo(mid, medicineNameEt.getText().toString(),
                            dateEt.getText().toString(), timeEt.getText().toString(), detailsEt.getText().toString(), null, null);
                    boolean inserted = medicineController.addMedicineInfo(medicineInfo);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(AddMedicineActivity.this, MedicineInfoActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.medicineDateEditText: {
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
                break;
            }
            case R.id.medicineTimeEditText: {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                timeEt.setText(hourOfDay + ":" + minute);
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
        Intent intent = new Intent(AddMedicineActivity.this, MedicineInfoActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }


}
