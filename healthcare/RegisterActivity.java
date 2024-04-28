package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText edName,edEmail,edPassword,edCPassword;
    Button register;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        edName = findViewById(R.id.book_name);
        edEmail = findViewById(R.id.book_address);
        edPassword = findViewById(R.id.book_pincode);
        edCPassword = findViewById(R.id.book_phone);
        register = findViewById(R.id.book_btn);
        tv = findViewById(R.id.Reg_tv);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edCPassword.getText().toString();


                Database database = new Database(getApplicationContext(),"HealthCare",null,1);
                
                if(name.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    // Check if any field is empty or not
                    Toast.makeText(RegisterActivity.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirm)==0){
                        // Check the password and conform password is same or not
                            if(isValid(password)){
                                // If password is valid then move to the login Activity
                                database.register(name,email,password);
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }else{
                                // When password is same but not a valid password
                                Toast.makeText(RegisterActivity.this, "Password is not valid, it must contains 8 letter, digit and special character", Toast.LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password and confirm password are not same", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

// Check the password is valid or not
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8) {
            return false;
        }else{
            for(int i=0 ; i<passwordhere.length() ; i++){
                if(Character.isLetter(passwordhere.charAt(i))){
                    f1=1;
                }
            }
            for(int j=0 ; j<passwordhere.length() ; j++){
                if(Character.isDigit(passwordhere.charAt(j))){
                    f2=1;
                }
            }
            for(int k=0 ; k<passwordhere.length() ; k++){
                char c=passwordhere.charAt(k);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }

}