package com.shihon.hotelmanagment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.MainActivity;
import com.shihon.hotelmanagment.R;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnGoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnGoMain = findViewById(R.id.btnGoMain);
        btnGoMain.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
