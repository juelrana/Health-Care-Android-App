package com.example.jamil.healthcareapp.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

/**
 * Created by software0 on 4/24/2016.
 */
public class DoctorAdapter extends ArrayAdapter<DoctorInfo> {
    TextView nameTv;
    TextView dateTv;
    TextView timeTv;
    TextView mobileTv;
    TextView emailTv;
    Context context;
    ArrayList<DoctorInfo> doctorList;

    public DoctorAdapter(Context context, ArrayList<DoctorInfo> doctorList) {
        super(context, R.layout.doctor_row_view, doctorList);
        this.context = context;
        this.doctorList = doctorList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.doctor_row_view, null);
        nameTv = (TextView) convertView.findViewById(R.id.doctorNameTextView);
        dateTv = (TextView) convertView.findViewById(R.id.dateTextView);
        timeTv = (TextView) convertView.findViewById(R.id.timeTextView);
        mobileTv = (TextView) convertView.findViewById(R.id.mobileTextView);
        emailTv = (TextView) convertView.findViewById(R.id.emailTextView);

        nameTv.setText("Dr. "+doctorList.get(position).getDoctorName().toString());
        dateTv.setText(doctorList.get(position).getAppointmentDate().toString());
        timeTv.setText(doctorList.get(position).getAppointmentTime().toString());
        mobileTv.setText(doctorList.get(position).getMobileNo().toString());
        emailTv.setText(doctorList.get(position).getEmailNo().toString());
        return convertView;
    }
}