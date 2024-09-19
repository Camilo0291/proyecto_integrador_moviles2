package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Usar un Handler para retrasar la acción de cambiar de actividad
        new Handler().postDelayed(() -> {
            // Crear el Intent para ir a la actividad de login
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);  // Lanzar la actividad
            finish();  // Cerrar esta actividad para que el usuario no pueda volver a ella
        }, 3000); // Retraso de 3000 milisegundos (3 segundos)
    }
}
