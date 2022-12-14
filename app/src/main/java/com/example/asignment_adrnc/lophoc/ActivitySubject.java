package com.example.asignment_adrnc.lophoc;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.asignment_adrnc.ActivityAddSubject;
import com.example.asignment_adrnc.ActivitySubjectlnformation;
import com.example.asignment_adrnc.Activityupdate;
import com.example.asignment_adrnc.MainActivity;
import com.example.asignment_adrnc.R;
import com.example.asignment_adrnc.adapter.adaptersubject;
import com.example.asignment_adrnc.database.database;
import com.example.asignment_adrnc.model.Subject;
import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject>ArrayListSubject;
    com.example.asignment_adrnc.database.database database;
    com.example.asignment_adrnc.adapter.adaptersubject adaptersubject;

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject =  findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String title = cursor.getString(1);
            int credit =  cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id,title,credit,time,place));
        }
        adaptersubject = new adaptersubject(ActivitySubject.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);

        cursor.moveToFirst();
        cursor.close();


        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivitySubject.this,ActivityStudent.class);
                int id_subject = ArrayListSubject.get(i).getId();
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
                startActivity(intent1);
                break;
            default:
                Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id==pos){
                Intent intent = new Intent(ActivitySubject.this, ActivitySubjectlnformation.class);

                intent.putExtra("id",id);
                String title =cursor.getString(1);
                int credit =cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);

                startActivity(intent);
            }
        }
    }

    //phuong thuc xoa
    public void delete(final int position){
        Dialog dialog = new Dialog(this);
        //nap layout
        dialog.setContentView(R.layout.dialogdelete);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.StudentYesdele);
        Button btnNo = dialog.findViewById(R.id.StudentNodele);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                database = new database(ActivitySubject.this);

                database.DeleteSubject(position);
                //xoa student
                database.DeleteSubjectStuden(position);
                Intent intent = new Intent(ActivitySubject.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == pos){
                Intent intent = new Intent(ActivitySubject.this, Activityupdate.class);


                String title=cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                //gui du lieu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }
}