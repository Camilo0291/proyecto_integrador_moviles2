package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {  // Cambié el nombre de la clase a Login

    clsDBSqlite db;  // Cambié a clsDBSqlite
    EditText username, password;
    TextView signupText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new clsDBSqlite(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupText = findViewById(R.id.signupText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String username = this.username.getText().toString();  // Usar 'this.username'
            String password = this.password.getText().toString();  // Cambié a 'this.password'

            if (db.checkUser(username, password)) {
                Toast.makeText(login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Redirigir a la pantalla principal (agrega la lógica para redirigir)
                Intent intent = new Intent(login.this, talleres.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el listener para el texto "Registrarse"
        signupText.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad RegisterActivity
            Intent intent = new Intent(login.this, activity_register.class);  // Cambié a ActivityRegister
            startActivity(intent);
        });
    }
}
