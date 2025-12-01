// ReceptionDashboardActivity.java
package com.shihon.hotelmanagment.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.shihon.hotelmanagment.R;

public class ReceptionDashboardActivity extends AppCompatActivity {

    TextView tvWelcome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_dashboard);

        tvWelcome = findViewById(R.id.tvWelcome);

        String username = getIntent().getStringExtra("username");
        String role = getIntent().getStringExtra("userRole");

        tvWelcome.setText("Welcome " + username + " (" + role + ")");
    }
}
