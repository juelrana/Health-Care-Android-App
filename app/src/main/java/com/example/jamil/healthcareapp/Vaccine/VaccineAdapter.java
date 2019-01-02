package com.example.jamil.healthcareapp.Vaccine;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.Doctor.DoctorInfo;
import com.example.jamil.healthcareapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Jamil on 4/25/2016.
 */
public class VaccineAdapter extends ArrayAdapter<VaccineInfo>{
    TextView nameTv;
    TextView dateTv;
    TextView timeTv;
    TextView detailsTv;
    Context context;
    ArrayList<VaccineInfo> vaccineList;

    public VaccineAdapter(Context context, ArrayList<VaccineInfo> vaccineList) {
        super(context, R.layout.vaccine_row_view, vaccineList);
        this.context = context;
        this.vaccineList = vaccineList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.vaccine_row_view, null);
        nameTv = (TextView) convertView.findViewById(R.id.vaccineNameTextView);
        dateTv = (TextView) convertView.findViewById(R.id.vDateTextView);
        timeTv = (TextView) convertView.findViewById(R.id.vTimeTextView);
        detailsTv = (TextView) convertView.findViewById(R.id.detailsTextView);


        nameTv.setText(vaccineList.get(position).getVaccineName().toString());
        dateTv.setText(vaccineList.get(position).getDate().toString());
        timeTv.setText(vaccineList.get(position).getTime().toString());
        detailsTv.setText(vaccineList.get(position).getDetails().toString());
        return convertView;
    }


}