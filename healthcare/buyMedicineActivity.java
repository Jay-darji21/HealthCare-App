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

public class buyMedicineActivity extends AppCompatActivity {
    ListView listView;
    Button btn_back,btn_cart;

    String[][] packeges = {
            {"Uprise-03 100IU Capsule","","","","50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule","","","","305"},
            {"Vitamin B complex Capsule","","","","448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","539"},
            {"Dolo 650 Tablet","","","","30"},
            {"Crocin 650 Advance Tablet","","","","50"},
            {"Strepsils Medicated Lozenges for sore throat","","","","40"},
            {"Tata 1mg Calcium + Vitamin D3","","","","30"},
            {"Faronia -XT Tablet","","","","130"}
    };

    String[] packege_details = {
            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pains\n" +
                    "Boosting immunity and increasing resistance against infraction",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose",
            "Provide relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n" +
                    "It helps reduce skin blemish and pigmentation\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messenger responsibility for fever and pain",
            "Helps relieve fever and bring down a high temperature\n" +
                    "Suitable for people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm an comforting feeling during score throat",
            "Reduce the risk of calcium deficiency, Rickets, and Osteoporosis\n" +
                    "Promotes mobility and flexibility of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or law intake of iron"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);

        btn_back = findViewById(R.id.medicine_back);
        btn_cart = findViewById(R.id.back_btn);
        listView=findViewById(R.id.health_details_img);

        // List view
        list = new ArrayList();
        for(int i=0 ; i<packeges.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packeges[i][0]);
            item.put("line2",packeges[i][1]);
            item.put("line3",packeges[i][2]);
            item.put("line4",packeges[i][3]);
            item.put("line5","Price " +packeges[i][4]+"/-");
            list.add(item);
        }
        ad = new SimpleAdapter(this,list,R.layout.multi_line,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(ad);

        // Itemclick listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(buyMedicineActivity.this,medicine_detailsActivity.class);
                it.putExtra("text1",packeges[position][0]);
                it.putExtra("text2",packege_details[position]);
                it.putExtra("text3",packeges[position][4]);
                startActivity(it);
            }
        });

        // Back button
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicineActivity.this,HomeActivity.class));
                finish();
            }
        });

    }
}