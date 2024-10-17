package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class activity_register extends AppCompatActivity {

    // Definimos las vistas
    EditText email, password;
    TextView sig;
    Button register;
    FirebaseAuth mAut = FirebaseAuth.getInstance();

    // Expresión regular para verificar que el correo tiene un dominio permitido (gmail.com, hotmail.com, yahoo.com)
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@(gmail\\.com|hotmail\\.com|yahoo\\.com)$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Conectamos las vistas con los componentes del layout
        email = findViewById(R.id.etEmailR);
        password = findViewById(R.id.etPasswordR);
        sig = findViewById(R.id.sigRegisterText);
        register = findViewById(R.id.registerButton);

        // Configuración del botón de registro
        register.setOnClickListener(view -> {
            String mEmail = email.getText().toString();
            String mPassword = password.getText().toString();

            // Verificación de que los campos no estén vacíos
            if (!mEmail.isEmpty() && !mPassword.isEmpty()) {
                // Verificamos que el correo tenga un dominio permitido
                if (isValidEmailDomain(mEmail)) {
                    // Intentamos registrar al usuario con Firebase Authentication
                    mAut.createUserWithEmailAndPassword(mEmail, mPassword)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(activity_register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), talleres.class));
                                } else {

                                    Toast.makeText(activity_register.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(activity_register.this, "El correo debe ser de un dominio válido", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity_register.this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener ir a "Iniciar sesión"
        sig.setOnClickListener(v -> {
            Intent intent = new Intent(activity_register.this, login.class);
            startActivity(intent);
        });
    }

    // Método para validar si el correo tiene un dominio permitido según la expresión regular
    private boolean isValidEmailDomain(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
