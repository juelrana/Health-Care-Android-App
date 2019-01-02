package com.example.jamil.healthcareapp.Member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamil.healthcareapp.Member.MemberInfo;
import com.example.jamil.healthcareapp.R;

import java.util.ArrayList;

/**
 * Created by Jamil on 4/20/2016.
 */
public class MemberAdapter extends ArrayAdapter<MemberInfo> {
    TextView nameTv;
    TextView relationTv;
    Context context;
    ArrayList<MemberInfo> memberList;

    public MemberAdapter(Context context, ArrayList<MemberInfo> memberList) {
        super(context, R.layout.member_row_view, memberList);
        this.context = context;
        this.memberList = memberList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.member_row_view, null);
        nameTv = (TextView) convertView.findViewById(R.id.nameTextView);
        relationTv = (TextView) convertView.findViewById(R.id.relationTextView);

        nameTv.setText(memberList.get(position).getName().toString());
        relationTv.setText( memberList.get(position).getRelation().toString());

        return convertView;
    }

}
