package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor name : Ajit Patel", "Hospital Address : Nikol", "Exp : 5yrs", "Mobile No : 9898989998", "600"},
                    {"Doctor name : Prasad Mehta", "Hospital Address : Bopal", "Exp : 6yrs", "Mobile No : 4545454554", "900"},
                    {"Doctor name : Swapnil Pawar", "Hospital Address : Satelight", "Exp : 15yrs", "Mobile No : 888955647", "500"},
                    {"Doctor name : Deepak Sharma", "Hospital Address : Asarava", "Exp : 8yrs", "Mobile No : 7845765489", "700"},
                    {"Doctor name : Ashok Yadav", "Hospital Address : Khadiya", "Exp : 4yrs", "Mobile No : 754854586", "800"},
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor name : Neelam Patil", "Hospital Address : Nikol", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
                    {"Doctor name : Swati Pawar", "Hospital Address : Bopal", "Exp : 6yrs", "Mobile No : 4545454454", "900"},
                    {"Doctor name : Neeraja Kale", "Hospital Address : Satelight", "Exp : 15yrs", "Mobile No : 888955647", "500"},
                    {"Doctor name : Mayuri Deshmukh", "Hospital Address : Asarava", "Exp : 8yrs", "Mobile No : 7857465489", "700"},
                    {"Doctor name : Minakshi Panda", "Hospital Address : Khadiya", "Exp : 4yrs", "Mobile No : 7584564588", "800"},
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor name : Seema Mehta", "Hospital Address : Nikol", "Exp : 5yrs", "Mobile No : 9898989898", "600"},
                    {"Doctor name : Pankaj Pawar", "Hospital Address : Bopal", "Exp : 6yrs", "Mobile No : 4545445454", "900"},
                    {"Doctor name : Monish Sharma", "Hospital Address : Satelight", "Exp : 15yrs", "Mobile No : 898955647", "500"},
                    {"Doctor name : Prasad Patel", "Hospital Address : Asarava", "Exp : 8yrs", "Mobile No : 7845465489", "700"},
                    {"Doctor name : Shrikant Pandya", "Hospital Address : Khadiya", "Exp : 4yrs", "Mobile No : 7585647586", "800"},
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor name : Dev Patel", "Hospital Address : Nikol", "Exp : 5yrs", "Mobile No : 9898999898", "600"},
                    {"Doctor name : Jeel Mehta", "Hospital Address : Bopal", "Exp : 6yrs", "Mobile No : 4545455454", "900"},
                    {"Doctor name : Naitik Pawar", "Hospital Address : Satelight", "Exp : 15yrs", "Mobile No : 898955647", "500"},
                    {"Doctor name : Atul Sharma", "Hospital Address : Asarava", "Exp : 8yrs", "Mobile No : 7845765489", "700"},
                    {"Doctor name : Manish Yadav", "Hospital Address : Khadiya", "Exp : 4yrs", "Mobile No : 7548764586", "800"},
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor name : Hit Patel", "Hospital Address : Nikol", "Exp : 5yrs", "Mobile No : 98989899898", "600"},
                    {"Doctor name : Bhavy Mehta", "Hospital Address : Bopal", "Exp : 6yrs", "Mobile No : 45454545454", "900"},
                    {"Doctor name : Harshil Pawar", "Hospital Address : Satelight", "Exp : 15yrs", "Mobile No : 8898955647", "500"},
                    {"Doctor name : Krish Sharma", "Hospital Address : Asarava", "Exp : 8yrs", "Mobile No : 78457465489", "700"},
                    {"Doctor name : Divyesh Yadav", "Hospital Address : Khadiya", "Exp : 4yrs", "Mobile No : 7548564586", "800"},
            };
    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList arrayList;
    HashMap<String,String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.health_details_title);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family physician")==0){
            doctor_details = doctor_details1;
        }else if(title.compareTo("Dieticion")==0){
            doctor_details=doctor_details2;
        }
        else if(title.compareTo("Dentist")==0){
            doctor_details=doctor_details3;
        }
        else if(title.compareTo("Surgeon")==0){
            doctor_details=doctor_details4;
        }
        else{
            doctor_details=doctor_details5;
        }
        arrayList = new ArrayList();
        for(int i=0 ; i<doctor_details.length ; i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons. fees "+doctor_details[i][4]+"/-");
            arrayList.add(item);
        }
        sa = new SimpleAdapter(this,arrayList,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView listView = findViewById(R.id.health_details_img);
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this,bookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[position][0]);
                it.putExtra("text3",doctor_details[position][1]);
                it.putExtra("text4",doctor_details[position][3]);
                it.putExtra("text5",doctor_details[position][4]);
                startActivity(it);
            }
        });




        btn = findViewById(R.id.back_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,findDoctorActivity.class));
                finish();
            }
        });




    }
}
