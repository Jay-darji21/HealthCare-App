package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    String[][] health_articles =
            {
                    {"Walking","","","","Click for more Details"},
                    {"Home care of COVID-19","","","","Click for more Details"},
                    {"Stop smoking","","","","Click for more Details"},
                    {"Menstrual Cramps","","","","Click for more Details"},
                    {"Healthy Gut","","","","Click for more Details"},
            };
    int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };
    Button btnBack;
    ListView listView;

    ArrayList list;
    HashMap<String,String> item;
    SimpleAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);

        // Back button
        btnBack = findViewById(R.id.back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity.class));
                finish();
            }
        });

        // ListView
        list = new ArrayList<String>();
        for (int i=0 ; i<health_articles.length ; i++){
            item = new HashMap<String,String>();
            item.put("line1",health_articles[i][0]);
            item.put("line2",health_articles[i][1]);
            item.put("line3",health_articles[i][2]);
            item.put("line4",health_articles[i][3]);
            item.put("line5",health_articles[i][4]);
            list.add(item);
        }
        listView = findViewById(R.id.health_details_img);
        ad = new SimpleAdapter(this,list,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(HealthArticlesActivity.this,HealthDetailsActivity.class);
                intent.putExtra("text1",health_articles[i][0]);
                intent.putExtra("text2",images[i]);
                startActivity(intent);
            }
        });
    }
}