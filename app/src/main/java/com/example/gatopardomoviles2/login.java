package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    clsDBSqlite db;
    EditText username, password;
    TextView signupText1;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new clsDBSqlite(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupText1 = findViewById(R.id.signupText1);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // Verificar que los campos no estén vacíos
            if (usernameText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(login.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return; // No continuar si hay campos vacíos
            }

            // Validación de credenciales
            if (db.checkUser(usernameText, passwordText)) {
                Toast.makeText(login.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, activity_panel.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(login.this, "¡Credenciales incorrectas!", Toast.LENGTH_SHORT).show();
            }
        });

        signupText1.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, activity_register.class);
            startActivity(intent);
        });
    }
}
