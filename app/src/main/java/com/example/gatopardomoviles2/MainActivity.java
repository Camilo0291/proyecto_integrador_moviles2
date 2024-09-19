package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Referenciar el logo
        ImageView logo = findViewById(R.id.logo);

        // Usar un Handler para retrasar la acción de cambiar de actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear el Intent para ir a la SecondActivity
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);  // Lanzar la actividad
                finish();  // Cerrar esta actividad para que el usuario no pueda volver a ella con el botón de "atrás"
            }
        }, 3000); // Retraso de 3000 milisegundos (3 segundos)
    }
}
