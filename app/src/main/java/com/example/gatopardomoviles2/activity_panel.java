package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_panel extends AppCompatActivity {

    EditText email, password;
    ImageView searchButton;
    Button registerButtonPanel;
    boolean isUserFound = false;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        // Inicializamos los elementos
        email = findViewById(R.id.etEmailP);
        password = findViewById(R.id.etPasswordP);
        auth = FirebaseAuth.getInstance();
        searchButton = findViewById(R.id.ibSearch);
        registerButtonPanel = findViewById(R.id.registerButttonpanel);

        // Configuramos el listener para el botón de búsqueda
        searchButton.setOnClickListener(v -> {
            String enteredEmail = email.getText().toString().trim();

            if (enteredEmail.isEmpty()) {
                Toast.makeText(activity_panel.this, "¡Por favor, ingrese un correo para buscar!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buscar el usuario en Firebase
            auth.fetchSignInMethodsForEmail(enteredEmail).addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult().getSignInMethods() != null) {
                    // Si el usuario existe
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        // Mostrar la contraseña guardada (esto es solo un ejemplo, las contraseñas no se deben mostrar)
                        // Firebase no permite obtener contraseñas directamente, pero puedes manejar la lógica de recuperación aquí
                        password.setText("Contraseña no se puede mostrar por seguridad");
                        Toast.makeText(activity_panel.this, "¡Datos encontrados!", Toast.LENGTH_SHORT).show();
                        isUserFound = true;
                    } else {
                        Toast.makeText(activity_panel.this, "¡Usuario no encontrado!", Toast.LENGTH_SHORT).show();
                        isUserFound = false;
                    }
                } else {
                    Toast.makeText(activity_panel.this, "¡Usuario no encontrado!", Toast.LENGTH_SHORT).show();
                    isUserFound = false;
                }
            });
        });

        // Listener para ir a(talleres)
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
