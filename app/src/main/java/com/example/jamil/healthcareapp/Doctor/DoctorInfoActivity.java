package com.example.jamil.healthcareapp.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jamil.healthcareapp.Controller.DoctorController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.PersonalActivity;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

public class DoctorInfoActivity extends AppCompatActivity {
    ListView listView;
    private DoctorController doctorController;
    private DoctorAdapter adapter;
    int mid;
    int did;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        listView = (ListView) findViewById(R.id.listView);
        View empty = findViewById(R.id.empty);
        listView.setEmptyView(empty);
        doctorController = new DoctorController(this);
         mid = getIntent().getIntExtra("mid", 0);
        final ArrayList<DoctorInfo> doctorList = doctorController.getAllDoctorInfo(mid);
        adapter = new DoctorAdapter(this, doctorList);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DoctorInfo doctorInfo = doctorList.get(position);
                did = doctorInfo.getId();
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_doctor_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(DoctorInfoActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add) {
            Intent intent = new Intent(DoctorInfoActivity.this, AddDoctorActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }else if (id == android.R.id.home) {
            Intent intent = new Intent(DoctorInfoActivity.this,PersonalActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Edit") {
            Intent intent = new Intent(DoctorInfoActivity.this, AddDoctorActivity.class);
            intent.putExtra("did", did);
            intent.putExtra("mid", mid);
            startActivity(intent);

        } else if (item.getTitle() == "Delete") {
            boolean deleted = doctorController.deleteDoctorInfo(did);
            if (deleted) {
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DoctorInfoActivity.this, DoctorInfoActivity.class);
                intent.putExtra("mid", mid);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Deleted Failed", Toast.LENGTH_LONG).show();
            }
        } else {
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DoctorInfoActivity.this, PersonalActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

}
