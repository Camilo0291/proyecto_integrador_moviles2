package com.example.gatopardomoviles2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_panel extends AppCompatActivity {

    EditText username1, email, password;
    ImageView searchButton;
    Button registerButtonPanel;
    boolean isUserFound = false;  // Bandera para saber si el usuario fue encontrado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        // Inicializamos los elementos
        username1 = findViewById(R.id.username1);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        searchButton = findViewById(R.id.ibSearch);
        registerButtonPanel = findViewById(R.id.registerButttonpanel);

        // Configuramos el listener para el botón de búsqueda
        searchButton.setOnClickListener(v -> {
            String enteredUsername = username1.getText().toString().trim();

            if (enteredUsername.isEmpty()) {
                Toast.makeText(activity_panel.this, "¡Por favor, ingrese un nombre de usuario para buscar!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buscar el usuario en la base de datos
            clsDBSqlite dbHelper = new clsDBSqlite(this); // Inicializamos dbHelper
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + clsDBSqlite.TABLE_NAME + " WHERE USERNAME = ?", new String[]{enteredUsername});

            if (cursor.moveToFirst()) {
                String dbUsername = cursor.getString(cursor.getColumnIndexOrThrow(clsDBSqlite.COL_1)); // Mejor usar getColumnIndexOrThrow
                String dbEmail = cursor.getString(cursor.getColumnIndexOrThrow(clsDBSqlite.COL_2));
                String dbPassword = cursor.getString(cursor.getColumnIndexOrThrow(clsDBSqlite.COL_3));

                username1.setText(dbUsername);
                email.setText(dbEmail);
                password.setText(dbPassword);

                Toast.makeText(activity_panel.this, "¡Datos encontrados!", Toast.LENGTH_SHORT).show();
                isUserFound = true;  // Se ha encontrado el usuario
            } else {
                Toast.makeText(activity_panel.this, "¡Usuario no encontrado!", Toast.LENGTH_SHORT).show();
                isUserFound = false;  // Usuario no encontrado
            }
            cursor.close();
        });

        // Configurar el listener para el botón de registro (Ir a talleres)
        registerButtonPanel.setOnClickListener(v -> {
            if (isUserFound) {
                Intent intent = new Intent(activity_panel.this, talleres.class);
                startActivity(intent);
            } else {
                Toast.makeText(activity_panel.this, "¡Debe buscar los datos del usuario!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
