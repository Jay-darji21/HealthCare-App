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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class medicine_detailsActivity extends AppCompatActivity {
    TextView packege,cost;
    EditText details;
    Button back,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicine_details);

        packege = findViewById(R.id.medicine_packeges);
        cost = findViewById(R.id.medicine_price);
        details = findViewById(R.id.medicine_list_detais);
        back = findViewById(R.id.medicine_backBtn);
        cart = findViewById(R.id.medicine_addToCart);

        // Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicine_detailsActivity.this,buyMedicineActivity.class));
                finish();
            }
        });

        Intent it = getIntent();
        packege.setText(it.getStringExtra("text1"));
        details.setKeyListener(null);
        details.setText(it.getStringExtra("text2"));
        cost.setText("Total cost : "+it.getStringExtra("text3")+"/-");

        // Add to cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                String product = packege.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthCare",null,1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(medicine_detailsActivity.this, "Product is already added!!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addToCart(username,product,price,"lab");
                    Toast.makeText(medicine_detailsActivity.this, "Product added in cart!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(medicine_detailsActivity.this,labTestActivity.class));
                    finish();
                }
            }
        });
    }
}