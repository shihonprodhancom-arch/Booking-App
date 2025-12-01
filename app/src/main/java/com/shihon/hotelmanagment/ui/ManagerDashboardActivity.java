package com.shihon.hotelmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.session.SessionManager;

public class ManagerDashboardActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_dashboard);

        session = new SessionManager(this);

        TextView tv = findViewById(R.id.tvWelcome);
        tv.setText("Welcome Manager, " + session.getUsername());

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            session.logout();
            startActivity(new Intent(ManagerDashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
