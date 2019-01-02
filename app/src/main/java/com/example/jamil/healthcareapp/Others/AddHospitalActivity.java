package com.example.jamil.healthcareapp.Others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.HospitalController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

public class AddHospitalActivity extends AppCompatActivity {
    EditText nameEt,phoneEt, emailEt,addressEt;
    private HospitalController hospitalController;
private HospitalInfo hospitalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        nameEt= (EditText) findViewById(R.id.hopital_name_ET);
        phoneEt= (EditText) findViewById(R.id.phone_no_ET);
        emailEt= (EditText) findViewById(R.id.email_no_ET);
        addressEt= (EditText) findViewById(R.id.address_ET);
        hospitalController=new HospitalController(this);

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
            Intent intent = new Intent(AddHospitalActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_save) {
            if(nameEt.getText().toString().isEmpty()){
                Toast.makeText(AddHospitalActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
            }else{
                hospitalInfo=new HospitalInfo(nameEt.getText().toString(),phoneEt.getText().toString(),emailEt.getText().toString(),

                        addressEt.getText().toString());

                boolean inserted =hospitalController .addHospitalInfo(hospitalInfo);
                if (inserted) {
                    Toast.makeText(getApplicationContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                    nameEt.getText().clear();
                    phoneEt.getText().clear();
                    emailEt.getText().clear();
                    addressEt.getText().clear();

                } else {
                    Toast.makeText(getApplicationContext(), "Insertion Failed", Toast.LENGTH_LONG).show();
                }


            }


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
