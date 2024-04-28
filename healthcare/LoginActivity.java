package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.Set;


public class LoginActivity extends AppCompatActivity {
    EditText edName,edPassword;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edName = findViewById(R.id.editTextName);
        edPassword = findViewById(R.id.editTextPassword);
        button = findViewById(R.id.login);
        textView = findViewById(R.id.register);
        Database database = new Database(getApplicationContext(),"HealthCare",null,1);
        
       // LoginButton onClick eventListener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edName.getText().toString();
                String password = edPassword.getText().toString();

                if(userName.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this, "Please fill all details!!", Toast.LENGTH_SHORT).show();
                }else{
                    if(database.login(userName,password)==1){
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName",userName);

                        editor.apply();
                        Log.d("LoginActivity", "Stored username: " + userName);

                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        //Toast.makeText(LoginActivity.this, "Login successfully!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                    
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}