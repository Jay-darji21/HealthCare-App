package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class bookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,date,time;
    TextView tv;
    Button btn,back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.tv_App);
        ed1 = findViewById(R.id.book_name);
        ed2 = findViewById(R.id.book_address);
        ed3 = findViewById(R.id.book_pincode);
        ed4 = findViewById(R.id.app_date);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons. fees "+fees+"/-");

        date = findViewById(R.id.app__date);
        time = findViewById(R.id.app_time);


        
        btn = findViewById(R.id.book_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = date.getText().toString();
                String t = time.getText().toString();
                if(d.length()==0 || t.length()==0){
                    Toast.makeText(bookAppointmentActivity.this, "Enter the date and time", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(bookAppointmentActivity.this, "Your appointment has conformed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back = findViewById(R.id.app_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bookAppointmentActivity.this,DoctorDetailsActivity.class));
                finish();
            }
        });
        
        
    }


}