package com.example.asignment_adrnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asignment_adrnc.R;
import com.example.asignment_adrnc.lophoc.ActivitySubject;
import com.example.asignment_adrnc.model.Subject;

import java.util.ArrayList;

public class adaptersubject extends BaseAdapter {

    private ActivitySubject context;

    private ArrayList<Subject>ArrayListSubject;

    public adaptersubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return ArrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listsubject,null);

        TextView tvSubjectTitle = view.findViewById(R.id.tvSubjectTitle);
        TextView tvCredit = view.findViewById(R.id.tvCredit);
        ImageButton imageDelete = view.findViewById(R.id.subjectindelete);
        ImageButton imageInformation = view.findViewById(R.id.subjectinformation);
        ImageButton imageUpdate= view.findViewById(R.id.subjectinupdate);
        Subject subject = ArrayListSubject.get(i);

        tvCredit.setText(subject.getNumber_of_credit()+"");
        tvSubjectTitle.setText(subject.getSubject_title());

        int id = subject.getId();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            context.delete(id);
            }
        });

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });
        return view;
    }
}
