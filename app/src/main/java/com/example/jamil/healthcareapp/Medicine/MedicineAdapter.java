package com.example.jamil.healthcareapp.Medicine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

/**
 * Created by software0 on 4/27/2016.
 */
public class MedicineAdapter extends ArrayAdapter<MedicineInfo> {
    TextView nameTv;
    TextView dateTv;
    TextView timeTv;
    TextView detailsTv;
    Context context;
    ArrayList<MedicineInfo> medicineList;

    public MedicineAdapter(Context context, ArrayList<MedicineInfo> medicineList) {
        super(context, R.layout.medicine_row_view, medicineList);
        this.context = context;
        this.medicineList = medicineList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.medicine_row_view, null);
        nameTv = (TextView) convertView.findViewById(R.id.medicineNameTextView);
        dateTv = (TextView) convertView.findViewById(R.id.mDateTextView);
        timeTv = (TextView) convertView.findViewById(R.id.mTimeTextView);
        detailsTv = (TextView) convertView.findViewById(R.id.medicineDetailsTextView);


        nameTv.setText(medicineList.get(position).getMedicineName().toString());
        dateTv.setText(medicineList.get(position).getDate().toString());
        timeTv.setText(medicineList.get(position).getTime().toString());
        detailsTv.setText(medicineList.get(position).getDetails().toString());
        return convertView;
    }

}
