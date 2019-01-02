package com.example.jamil.healthcareapp.Others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.R;
import com.example.jamil.healthcareapp.Vaccine.VaccineInfo;

import java.util.ArrayList;

/**
 * Created by Jamil on 4/30/2016.
 */
public class HospitalAdapter extends ArrayAdapter<HospitalInfo> {
    TextView nameTv;
    TextView phoneTv;
    TextView emailTv;
    TextView addressTv;
    Context context;
    ArrayList<HospitalInfo> hospitalList;

    public HospitalAdapter(Context context, ArrayList<HospitalInfo> hospitalList) {
        super(context, R.layout.hospital_row_view, hospitalList);
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.hospital_row_view, null);
        nameTv = (TextView) convertView.findViewById(R.id.hTextView);
        phoneTv = (TextView) convertView.findViewById(R.id.pTextView);
        emailTv = (TextView) convertView.findViewById(R.id.eTextView);
        addressTv = (TextView) convertView.findViewById(R.id.aTextView);


        nameTv.setText(hospitalList.get(position).getName().toString());
        phoneTv.setText(hospitalList.get(position).getPhone().toString());
        emailTv.setText(hospitalList.get(position).getEmail().toString());
        addressTv.setText(hospitalList.get(position).getAddress().toString());
        return convertView;
    }


}