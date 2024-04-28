package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthDetailsActivity extends AppCompatActivity {
    ImageView img;
    TextView title;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_details);

        img = findViewById(R.id.health_details_img);
        title = findViewById(R.id.health_details_title);
        back = findViewById(R.id.back_btn);

        // Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthDetailsActivity.this,HealthArticlesActivity.class));
                finish();
            }
        });

        // Image and title
        Intent it = getIntent();
        title.setText(it.getStringExtra("text1"));
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }
    }
}