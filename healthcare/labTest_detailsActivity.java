package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class labTest_detailsActivity extends AppCompatActivity {
    TextView packages,cost;
    EditText details;
    Button back,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_details);

        packages = findViewById(R.id.medicine_packeges);
        cost = findViewById(R.id.medicine_price);
        details = findViewById(R.id.medicine_list_detais);
        back = findViewById(R.id.medicine_backBtn);
        cart = findViewById(R.id.medicine_addToCart);

        details.setKeyListener(null);
        Intent intent = getIntent();
        packages.setText(intent.getStringExtra("text1"));
        details.setText(intent.getStringExtra("text2"));
        cost.setText("Total cost : "+intent.getStringExtra("text3")+"/-");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(labTest_detailsActivity.this,labTestActivity.class));
                finish();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = packages.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthCare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(labTest_detailsActivity.this, "Product is already added!!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addToCart(username,product,price,"lab");
                    Toast.makeText(labTest_detailsActivity.this, "Product added in cart!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(labTest_detailsActivity.this,labTestActivity.class));
                    finish();
                }
            }
        });


    }
}