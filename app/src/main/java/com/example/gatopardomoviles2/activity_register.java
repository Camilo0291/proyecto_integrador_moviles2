package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class activity_register extends AppCompatActivity {

    EditText username, password;
    TextView sigRegisterText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Inicializar los campos y botones
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sigRegisterText = findViewById(R.id.sigRegisterText);
        loginButton = findViewById(R.id.loginButton);


        sigRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_register.this, login.class);
                startActivity(intent);
            }
        });


    }
}