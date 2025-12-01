package com.shihon.hotelmanagment.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;

public class SettingsActivity extends AppCompatActivity {

    TextView txtUsername, txtRole, txtVersion;
    LinearLayout btnChangePassword, btnLogout;
    Switch themeSwitch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txtUsername = findViewById(R.id.txtUsername);
        txtRole = findViewById(R.id.txtRole);
        txtVersion = findViewById(R.id.txtVersion);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);
        themeSwitch = findViewById(R.id.switchTheme);

        // Current User info (Login থেকে পাঠানো)
        String username = getIntent().getStringExtra("username");
        String role = getIntent().getStringExtra("userRole");

        txtUsername.setText("Username: " + username);
        txtRole.setText("Role: " + role);

        txtVersion.setText("App Version: 1.0.0");

        // Theme Switch
        SharedPreferences pref = getSharedPreferences("theme", MODE_PRIVATE);
        boolean dark = pref.getBoolean("darkMode", false);
        themeSwitch.setChecked(dark);

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("darkMode", isChecked);
            editor.apply();
            recreate(); // UI refresh
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            Intent i = new Intent(SettingsActivity.this, com.shihon.hotelmanagment.ui.LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        // Change Password Button
        btnChangePassword.setOnClickListener(v -> {
            Intent i = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
            startActivity(i);
        });
    }
}
