package com.example.asignment_adrnc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.asignment_adrnc.fb.FacebookActivity;
import com.example.asignment_adrnc.lophoc.ActivitySubject;
import com.example.asignment_adrnc.maps.MapsActivity;
import com.example.asignment_adrnc.news.NewActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imgstudent,imgmap,imgnew,imgfb;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgstudent=findViewById(R.id.image1);
        imgmap=findViewById(R.id.image2);
        imgnew=findViewById(R.id.image3);
        imgfb=findViewById(R.id.image4);
        imgstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ActivitySubject.class);
                startActivity(intent);
            }
        });
        imgmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        imgfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });
        imgnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });
    }
}