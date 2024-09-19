
package com.example.gatopardomoviles2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // username = findViewById(R.id.username);
        // password = findViewById(R.id.password);
        // loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
                    Toast.makeText(login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}