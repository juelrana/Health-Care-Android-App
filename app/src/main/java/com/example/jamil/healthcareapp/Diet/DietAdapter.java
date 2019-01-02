package com.example.jamil.healthcareapp.Diet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.Doctor.DoctorInfo;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

/**
 * Created by Jamil on 4/25/2016.
 */
public class DietAdapter extends ArrayAdapter<DietInfo> {
    TextView mealNameTv;
    TextView menuTv;
    TextView dateTv;
    TextView timeTv;

    Context context;
    ArrayList<DietInfo> dietList;

    public DietAdapter(Context context, ArrayList<DietInfo> dietList) {
        super(context, R.layout.diet_row_view,dietList);
        this.context = context;
        this.dietList = dietList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.diet_row_view, null);
        mealNameTv = (TextView) convertView.findViewById(R.id.mealNameTextView);
        dateTv = (TextView) convertView.findViewById(R.id.dietDateTextView);
        timeTv = (TextView) convertView.findViewById(R.id.dietTimeTextView);
        menuTv = (TextView) convertView.findViewById(R.id.menuTextView);


        mealNameTv.setText(dietList.get(position).getMealName().toString());
        dateTv.setText(dietList.get(position).getDate().toString());
        timeTv.setText(dietList.get(position).getTime().toString());
        menuTv.setText(dietList.get(position).getMenu().toString());

        return convertView;
    }

}