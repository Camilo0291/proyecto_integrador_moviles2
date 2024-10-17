package com.example.gatopardomoviles2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText email, password;
    TextView signup;
    Button login;
    FirebaseAuth mAut = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enlazamos las vistas con los elementos del layout
        email = findViewById(R.id.etEmailS);
        password = findViewById(R.id.etPasswordS);
        signup = findViewById(R.id.signupText1);
        login = findViewById(R.id.loginButton);

        // Configuración del boton inicio de sesión
        login.setOnClickListener(v -> {
            String mEmail = email.getText().toString(); // Obtener el email ingresado
            String mPassword = password.getText().toString(); // Obtener la contraseña ingresada

            // Verificar que los campos no estén vacíos
            if (mEmail.isEmpty() || mPassword.isEmpty()) {
                Toast.makeText(login.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Iniciar sesión con Firebase Authentication
            mAut.signInWithEmailAndPassword(mEmail, mPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, activity_panel.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(login.this, "¡Credenciales incorrectas!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Listener para ir a "Registrarse"
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, activity_register.class);
            startActivity(intent);
        });
    }
}
