package com.example.jamil.healthcareapp.Prescription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jamil.healthcareapp.R;

public class AddPrescriptionActivity extends AppCompatActivity {
    int mid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        mid=getIntent().getIntExtra("mid",0);
        Toast.makeText(this, String.valueOf(mid), Toast.LENGTH_SHORT).show();

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
            return true;
        } else if (id == R.id.action_save) {

            return true;
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(AddPrescriptionActivity.this, PrescriptionActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddPrescriptionActivity.this, PrescriptionActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

}
