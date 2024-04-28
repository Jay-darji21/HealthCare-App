package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Button;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class OrderDetailsActivity extends AppCompatActivity {
    ListView listView;
    Button back_btn;
    ArrayList arr;
    HashMap<String,String> item;
    float totalCost=0,totalAmount=0;
    SimpleAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);

        back_btn = findViewById(R.id.back_btn);
        listView = findViewById(R.id.health_details_img);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        // Get the data from database
        Database db = new Database(getApplicationContext(),"healthCare",null,1);
        ArrayList dbData = db.getCartData(username,"lab");
        float cost = 0;

        String packeges[][] = {};
        packeges = new String[dbData.size()][];
        for (int i=0 ; i<packeges.length;i++){
            packeges[i]=new String[5];
        }

        // Set total cost of cart
        for(int i=0 ; i<dbData.size(); i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(Pattern.quote("$"));
            packeges[i][0] = strData[0];
            packeges[i][4]="Total cost : "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }




        // Show the data in Listview
        arr = new ArrayList();
        for(int i=0 ; i<packeges.length ; i++){
            item = new HashMap<String,String>();
            item.put("line1",packeges[i][0]);
            item.put("line2",packeges[i][1]);
            item.put("line3",packeges[i][2]);
            item.put("line4",packeges[i][3]);
            item.put("line5",packeges[i][4]);
            arr.add(item);
        }
        listView = findViewById(R.id.health_details_img);
        ad = new SimpleAdapter(this,arr,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(ad);



    }
}