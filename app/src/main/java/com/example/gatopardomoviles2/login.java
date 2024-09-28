package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText username, password;
    TextView signupText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar los campos y botones
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupText = findViewById(R.id.signupText);
        loginButton = findViewById(R.id.loginButton);

        // Configurar el listener para el texto "Registrarse"
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a la actividad RegisterActivity
                Intent intent = new Intent(login.this, activity_register.class);  // Corregido a login.this
                startActivity(intent);  // Iniciar la nueva actividad
            }
        });
    }
}
