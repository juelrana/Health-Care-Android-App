package com.example.jamil.healthcareapp.Vaccine;

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

import com.example.jamil.healthcareapp.Controller.VaccineController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.PersonalActivity;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;


public class VaccineInfoActivity extends AppCompatActivity {
    ListView vaccineListView;
    ListView previousListView;
    private VaccineController vaccineController;
    private VaccineAdapter adapter;
    int mid;
    int vid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);
        vaccineListView = (ListView) findViewById(R.id.VaccineListView);
        previousListView= (ListView) findViewById(R.id.previousVaccineListView);
        View empty = findViewById(R.id.empty);
        vaccineListView.setEmptyView(empty);
        View empty2 = findViewById(R.id.empty2);
        previousListView.setEmptyView(empty2);

        mid=getIntent().getIntExtra("mid",0);
        vaccineController=new VaccineController(this);
        final ArrayList<VaccineInfo> vaccineList = vaccineController.getAllVaccineInfo(mid);
        adapter = new VaccineAdapter(this, vaccineList);
        vaccineListView.setAdapter(adapter);
        registerForContextMenu(vaccineListView);
        vaccineListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                VaccineInfo vaccineInfo = vaccineList.get(position);
                vid = vaccineInfo.getId();
                return false;
            }
        });

        final ArrayList<VaccineInfo> vaccineList2 = vaccineController.getAllPreviousVaccineInfo(mid);
        adapter = new VaccineAdapter(this, vaccineList2);
        previousListView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_vaccine_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(VaccineInfoActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_add){

            Intent intent=new Intent(VaccineInfoActivity.this,AddVaccineActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }else if (id == android.R.id.home) {
            Intent intent = new Intent(VaccineInfoActivity.this,PersonalActivity.class);
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
            Intent intent = new Intent(VaccineInfoActivity.this, AddVaccineActivity.class);
            intent.putExtra("vid", vid);
            intent.putExtra("mid", mid);
            startActivity(intent);

        } else if (item.getTitle() == "Delete") {
            boolean deleted = vaccineController.deleteVaccineInfo(vid);
            if (deleted) {
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(VaccineInfoActivity.this, VaccineInfoActivity.class);
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
        Intent intent = new Intent(VaccineInfoActivity.this,PersonalActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }

}
