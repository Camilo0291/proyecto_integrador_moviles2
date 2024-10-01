package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_register extends AppCompatActivity {  // Cambié el nombre de la clase

    clsDBSqlite db;  // Cambié a clsDBSqlite
    EditText username, password, email;  // Agregué 'email' para que puedas registrar el correo
    TextView sigRegisterText;
    Button registerButton;  // Corregí el nombre del botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  // Asegúrate de que el layout sea el correcto

        db = new clsDBSqlite(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);  // Asegúrate de que tienes un EditText para email en el layout
        sigRegisterText = findViewById(R.id.sigRegisterText);
        registerButton = findViewById(R.id.registerButton);  // Corregí el nombre del botón

        registerButton.setOnClickListener(v -> {
            String username = this.username.getText().toString();  // Corregí el método a getText()
            String password = this.password.getText().toString();
            String email = this.email.getText().toString();  // Obteniendo el email

            if (db.insertUser(username, email, password)) {  // Agregué el email al método
                Toast.makeText(activity_register.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity_register.this, talleres.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(activity_register.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el listener para el texto "Iniciar Sesión"
          sigRegisterText.setOnClickListener(v -> {
            // Crear un Intent para ir a la actividad de inicio de sesión
            Intent intent = new Intent(activity_register.this, login.class);  // Asegúrate de que la clase sea la correcta
            startActivity(intent);
        });
    }
}
