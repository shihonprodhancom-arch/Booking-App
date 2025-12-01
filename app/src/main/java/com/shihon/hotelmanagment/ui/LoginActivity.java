package com.shihon.hotelmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.UserEntity;
import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        db = AppDatabase.getInstance(this);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            UserEntity user = db.userDao().getUserByCredentials(username, password);

            if (user == null) {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                return;
            }

            // ---------------- Role-based Dashboard ----------------
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", user.getUsername());
            intent.putExtra("userRole", user.getRole());
            startActivity(intent);
            finish(); // Login activity close
        });
    }
}
