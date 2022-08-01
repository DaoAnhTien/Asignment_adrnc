package com.example.asignment_adrnc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySubjectlnformation extends AppCompatActivity {
    TextView txtTitle,txtCredit,txtTime,txtPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectlnformation);

        txtTitle = findViewById(R.id.txtSubjectTitle);
        txtCredit = findViewById(R.id.txtSubjectCredit);
        txtTime = findViewById(R.id.txtSubjectTime);
        txtPlace = findViewById(R.id.txtSubjectPlace);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int credit  =intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        txtTitle.setText(title);
        txtCredit.setText(credit+"");
        txtTime.setText(time);
        txtPlace.setText(place);
    }
}