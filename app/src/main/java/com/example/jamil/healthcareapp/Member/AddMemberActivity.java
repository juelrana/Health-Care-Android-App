package com.example.jamil.healthcareapp.Member;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.MemberController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMemberActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameEt;
    EditText ageEt;
    EditText heightEt;
    EditText weightEt;
    Spinner bloodSp;
    Spinner relationSp;
    EditText majorDiseasesEt;
    private MemberInfo memberInfo;
    private MemberController memberController;
    private SimpleDateFormat dateFormatter;
    DatePickerDialog datePickerDialog;
    int mid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);


        memberController = new MemberController(this);

        nameEt = (EditText) findViewById(R.id.nameEditText);
        ageEt = (EditText) findViewById(R.id.ageEditText);
        heightEt = (EditText) findViewById(R.id.heightEditText);
        weightEt = (EditText) findViewById(R.id.weightEditText);
        bloodSp = (Spinner) findViewById(R.id.bloodDropDownList);
        bloodSp.requestFocus();
        relationSp = (Spinner) findViewById(R.id.relationDropDownList);
        relationSp.requestFocus();
        majorDiseasesEt = (EditText) findViewById(R.id.diseasesEditText);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ageEt.setInputType(InputType.TYPE_NULL);
        ageEt.setOnClickListener(this);

        mid = getIntent().getIntExtra("mid", 0);
        memberController = new MemberController(this);
        if (mid != 0) {
            nameEt.setText(memberController.getMemberInfo(mid).getName().toString());
            ageEt.setText(memberController.getMemberInfo(mid).getAge().toString());
            heightEt.setText(memberController.getMemberInfo(mid).getHeight().toString());
            weightEt.setText(memberController.getMemberInfo(mid).getWeight().toString());
            bloodSp.getOnItemSelectedListener();
            majorDiseasesEt.setText(memberController.getMemberInfo(mid).getMajorDiseases().toString());
            setTitle("Update Form");

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_member, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        } else if (id == R.id.action_save) {
            if (mid != 0) {
                if (nameEt.getText().toString().isEmpty() ||  relationSp.getSelectedItem().toString().equals("Select")) {
                    Toast.makeText(AddMemberActivity.this, "Enter name & Select relationship", Toast.LENGTH_SHORT).show();

                } else {
                    memberInfo = new MemberInfo(nameEt.getText().toString(), ageEt.getText().toString(), heightEt.getText().toString(),
                            weightEt.getText().toString(), bloodSp.getSelectedItem().toString(), relationSp.getSelectedItem().toString(),
                            majorDiseasesEt.getText().toString());

                    boolean updated = memberController.updateMemberInfo(mid, memberInfo);
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(AddMemberActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();
                    }

                }
            } else {

                if (nameEt.getText().toString().isEmpty() ||  relationSp.getSelectedItem().toString().equals("Select")) {
                    Toast.makeText(AddMemberActivity.this, "Enter name & Select relationship", Toast.LENGTH_SHORT).show();

                } else {

                    memberInfo = new MemberInfo(nameEt.getText().toString(), ageEt.getText().toString(), heightEt.getText().toString(),
                            weightEt.getText().toString(), bloodSp.getSelectedItem().toString(), relationSp.getSelectedItem().toString(),
                            majorDiseasesEt.getText().toString());

                    boolean inserted = memberController.addMemberInfo(memberInfo);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                        nameEt.getText().clear();
                        ageEt.getText().clear();
                        heightEt.getText().clear();
                        weightEt.getText().clear();
                        majorDiseasesEt.getText().clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ageEditText: {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                ageEt.setText(dateFormatter.format(newDate.getTime()));
                            }
                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            }
        }
    }



}
