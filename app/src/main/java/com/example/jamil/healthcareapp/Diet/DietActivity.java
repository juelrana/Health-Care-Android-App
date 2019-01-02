package com.example.jamil.healthcareapp.Diet;

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

import com.example.jamil.healthcareapp.Controller.DietController;
import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.PersonalActivity;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {
    ListView dietListView;
    ListView upcomingListView;
    ListView previousDietListView;
    private DietAdapter adapter;
    private DietController dietController;
    private int mid;
    private int did;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        dietListView = (ListView) findViewById(R.id.dietListView);
       upcomingListView= (ListView) findViewById(R.id.upcomingDietListView);
        previousDietListView= (ListView) findViewById(R.id.previousDietListView);
        mid = getIntent().getIntExtra("mid", 0);
        dietController = new DietController(this);
        final ArrayList<DietInfo> dietList = dietController.getAllDietInfo(mid);
        adapter = new DietAdapter(this, dietList);

        dietListView.setAdapter(adapter);
        registerForContextMenu(dietListView);
        dietListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DietInfo dietInfo = dietList.get(position);
                did = dietInfo.getId();
                return false;
            }
        });

        final ArrayList<DietInfo> dietList2 = dietController.getAllDietInfoUpComingDate(mid);
        adapter = new DietAdapter(this, dietList2);
        upcomingListView.setAdapter(adapter);

        registerForContextMenu(upcomingListView);
        upcomingListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DietInfo dietInfo = dietList2.get(position);
                did = dietInfo.getId();
                return false;
            }
        });
        final ArrayList<DietInfo> dietList3 = dietController.getAllDietInfoPreviousDate(mid);
        adapter = new DietAdapter(this, dietList3);
        previousDietListView.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_diet_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(DietActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add) {
            Intent intent = new Intent(DietActivity.this, AddDietActivity.class);
            intent.putExtra("mid", mid);
            startActivity(intent);
            return true;
        }else if (id == android.R.id.home) {
            Intent intent = new Intent(DietActivity.this,PersonalActivity.class);
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
            Intent intent = new Intent(DietActivity.this, AddDietActivity.class);
            intent.putExtra("did", did);
            intent.putExtra("mid", mid);
            startActivity(intent);

        } else if (item.getTitle() == "Delete") {
            boolean deleted = dietController.deleteDietInfo(did);
            if (deleted) {
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DietActivity.this, DietActivity.class);
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
        Intent intent = new Intent(DietActivity.this,PersonalActivity.class);
        intent.putExtra("mid", mid);
        startActivity(intent);
    }


}