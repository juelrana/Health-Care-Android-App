package com.example.jamil.healthcareapp.Diet;

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

import com.example.jamil.healthcareapp.Controller.DietController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDietActivity extends AppCompatActivity implements View.OnClickListener {
    private int mid;
    private int did;
    EditText mealNameEt;
    EditText menuEt;
    EditText dateEt;
    EditText timeEt;
    private DietInfo dietInfo;
    private DietController dietController;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet);


        dietController = new DietController(this);
        mid = getIntent().getIntExtra("mid", 0);
        did = getIntent().getIntExtra("did", 0);

        mealNameEt = (EditText) findViewById(R.id.mealEditText);
        menuEt = (EditText) findViewById(R.id.menuEditText);
        dateEt = (EditText) findViewById(R.id.dateEditText);
        timeEt = (EditText) findViewById(R.id.timeEditText);


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        timeEt.setInputType(InputType.TYPE_NULL);
        timeEt.setOnClickListener(this);
        dateEt.setInputType(InputType.TYPE_NULL);
        dateEt.setOnClickListener(this);

        if (did != 0) {
            mealNameEt.setText(dietController.getDietInfo(did).getMealName().toString());
            menuEt.setText(dietController.getDietInfo(did).getMenu().toString());
            dateEt.setText(dietController.getDietInfo(did).getDate().toString());
            timeEt.setText(dietController.getDietInfo(did).getTime().toString());
            setTitle("Update Form");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_diet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(AddDietActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_save) {

            if (did != 0) {
                if (mealNameEt.getText().toString().isEmpty() || dateEt.getText().toString().isEmpty()
                        || menuEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Required Value", Toast.LENGTH_LONG).show();
                } else {

                    dietInfo = new DietInfo(mid, mealNameEt.getText().toString(), menuEt.getText().toString(),
                            dateEt.getText().toString(), timeEt.getText().toString());

                    boolean updated = dietController.updateDietInfo(did, dietInfo);
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddDietActivity.this, DietActivity.class);
                        intent.putExtra("mid", mid);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                    }


                }


            } else {
                if (mealNameEt.getText().toString().isEmpty() || dateEt.getText().toString().isEmpty()
                        || menuEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Required Value", Toast.LENGTH_LONG).show();

                } else {

                    dietInfo = new DietInfo(mid, mealNameEt.getText().toString(), menuEt.getText().toString(),
                            dateEt.getText().toString(), timeEt.getText().toString());
                    boolean inserted = dietController.addDietInfo(dietInfo);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                        mealNameEt.getText().clear();
                        menuEt.getText().clear();
                        dateEt.getText().clear();
                        timeEt.getText().clear();

                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(AddDietActivity.this, DietActivity.class);
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
                                dateEt.setText(dateFormatter.format(newDate.getTime()));
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
            break;

            case R.id.timeEditText: {
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
        Intent intent = new Intent(AddDietActivity.this, DietActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }
}
