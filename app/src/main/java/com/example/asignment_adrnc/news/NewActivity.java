package com.example.asignment_adrnc.news;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asignment_adrnc.R;
import com.example.asignment_adrnc.Xmlparse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class    NewActivity extends AppCompatActivity {
    ArrayList<String> arrayList= new ArrayList<>();
    ArrayList<String> arrayLink= new ArrayList<>();
    ArrayAdapter adapter;
    Intent intent;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView=findViewById(R.id.listview);
        AsyncTask<String,Void,String>  content= new RSSFeed().execute("https://vnexpress.net/rss/giao-duc.rss");
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
    }
    public  class RSSFeed extends AsyncTask<String,Void,String>{
        //lay du lieu tu server
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content= new StringBuilder();
            try {
                URL url= new URL(strings[0]);// lây dữ liẹu tai vị trí đàu tiên
                InputStreamReader reader= new InputStreamReader(url.openConnection().getInputStream());//mỏw kết nối để lấy dữ liệu
                BufferedReader bufferedReader=new BufferedReader(reader);// đọc dữ liệu tại  reader
                String line ="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);// đọc và đưa vào dong line
                }
                bufferedReader.close();// tắt việc đock
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {//
            super.onPostExecute(s);
            Xmlparse xmlparse= new Xmlparse();
            try {
                Document document= xmlparse.getdocument(s);
                NodeList nodeList= document.getElementsByTagName("item");//lấy dữ liệu tại item có tag name là titel
                String title="";
                for(int i=0;nodeList.getLength()>i;i++){
                    Element element=(Element) nodeList.item(i);//lay ve item i
                    title =xmlparse.getValues(element,"title")+"\n";
                    arrayList.add(title);// đỏ dữ titel vào aray
                    arrayLink.add(xmlparse.getValues(element,"link"));
                }
                adapter.notifyDataSetChanged();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }

}