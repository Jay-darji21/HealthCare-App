package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class labTestActivity extends AppCompatActivity {
    private String packeges[][] ={
            {"Package 1 : Full body check up","","","","999"},
            {"Package 2 : Blood Glucose Fasting","","","","299"},
            {"Package 3 : Covid-19 Antibody IgG","","","","899"},
            {"Package 4 : Thyroid check","","","","499"},
            {"Package 5 : Immunity check","","","","599"}
    };
    private String packege_details[] = {
            "Blood Glucose Fasting\n"+"Complete Hemogram\n"+
             "HbAlc\n"+"Iron Studies\n"+"Kidney Function Test\n"+
             "LDH Lactate Dehydrogenase,Serum\n"+"Lipid profile\n"+
             "Liver function test","Blood Glucose Fasting","COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n"+
                    "CRP ( C Reactive Protein) Quantitative , Serum\n"+
                    "Tron Studies\n"+
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile"
    };

    ListView listView;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter ad;
    Button back;
    Button cart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);

        listView = findViewById(R.id.health_details_img);
        back = findViewById(R.id.medicine_back);
        cart = findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labTestActivity.this,HomeActivity.class));
                finish();
            }
        });
        list = new ArrayList();
        for (int i=0 ; i<packeges.length ; i++){
            item = new HashMap<String,String>();
            item.put("line1",packeges[i][0]);
            item.put("line2",packeges[i][1]);
            item.put("line3",packeges[i][2]);
            item.put("line4",packeges[i][3]);
            item.put("line5","Total cost : "+packeges[i][4]+"/-");
            list.add(item);
        }

        ad = new SimpleAdapter(this,list,R.layout.multi_line,new String[]{"line1","line2" ,"line3","line4","line5"}, new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(labTestActivity.this,labTest_detailsActivity.class);
                it.putExtra("text1",packeges[i][0]);
                it.putExtra("text2",packege_details[i]);
                it.putExtra("text3",packeges[i][4]);

                startActivity(it);
            }
        });

        cart = findViewById(R.id.back_btn);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labTestActivity.this,CartLabActivity.class));
            }
        });
    }
}