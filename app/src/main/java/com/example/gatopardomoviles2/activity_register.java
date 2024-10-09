package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_register extends AppCompatActivity {

    clsDBSqlite db;
    EditText username, password, email;
    TextView sigRegisterText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar la base de datos y las vistas
        db = new clsDBSqlite(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        sigRegisterText = findViewById(R.id.sigRegisterText);
        registerButton = findViewById(R.id.registerButton);

        // Listener para el botón de registro
        registerButton.setOnClickListener(v -> {
            String enteredUsername = username.getText().toString().trim();
            String enteredPassword = password.getText().toString().trim();
            String enteredEmail = email.getText().toString().trim();



            // Validar campos vacíos
            if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || enteredEmail.isEmpty()) {
                Toast.makeText(activity_register.this, "¡Por favor, completa todos los campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Intentar registrar al usuario en la base de datos
            if (db.insertUser(enteredUsername, enteredEmail, enteredPassword)) {
                Toast.makeText(activity_register.this, "¡Usuario registrado exitosamente!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_register.this, activity_panel.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(activity_register.this, "¡Error al registrar usuario!", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener para el texto de "Iniciar Sesión"
        sigRegisterText.setOnClickListener(v -> {
            Intent intent = new Intent(activity_register.this, login.class);
            startActivity(intent);
        });
    }
}
