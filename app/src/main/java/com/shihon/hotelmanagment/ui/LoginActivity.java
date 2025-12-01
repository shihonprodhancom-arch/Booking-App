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

            switch (user.getRole()) {
                case "admin":
                    startActivity(new Intent(this, AdminDashboardActivity.class));
                    break;
                case "manager":
                    startActivity(new Intent(this, ManagerDashboardActivity.class));
                    break;
                case "reception":
                    startActivity(new Intent(this, ReceptionDashboardActivity.class));
                    break;
            }
            finish(); // Login activity close
        });
    }
}
