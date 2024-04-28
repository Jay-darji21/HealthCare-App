package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bookLabTestActivity extends AppCompatActivity {
    EditText name,address,pincode,number;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_lab_test);

        name = findViewById(R.id.book_name);
        address = findViewById(R.id.book_address);
        pincode = findViewById(R.id.book_pincode);
        number = findViewById(R.id.book_phone);
        book = findViewById(R.id.book_btn);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String address1 = address.getText().toString();
                String pincode1 = pincode.getText().toString();
                String number1 = number.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                if(name1.length()==0 || address1.length()==0 || pincode1.length()==0 || number1.length()==0){
                    Toast.makeText(bookLabTestActivity.this, "Please fill all the details!!", Toast.LENGTH_SHORT).show();
                }else if(number1.length()!=10){
                    Toast.makeText(bookLabTestActivity.this, "Enter valid number!!", Toast.LENGTH_SHORT).show();
                }else if(pincode1.length()!=6){
                    Toast.makeText(bookLabTestActivity.this, "Enter valid pincode!!", Toast.LENGTH_SHORT).show();
                }else{
                        Database db = new Database(getApplicationContext(),"healthCare",null,1);
                        db.addOrder(username,name1,address1,pincode1,number1,"lab");
                        db.removeCart(username,"lab");
                        Toast.makeText(bookLabTestActivity.this, "Booking successfully!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(bookLabTestActivity.this,HomeActivity.class));
                    finish();
                }
            }
        });


    }
}