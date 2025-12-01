package com.shihon.hotelmanagment.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.UserEntity;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsername, tvRole;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.tvUsername);
        tvRole = findViewById(R.id.tvRole);

        String username = getIntent().getStringExtra("username");

        if (username != null) {
            AppDatabase db = AppDatabase.getInstance(this);
            UserEntity user = db.userDao().getUserByUsername(username);

            if (user != null) {
                tvUsername.setText(user.getUsername());
                tvRole.setText(user.getRole());
            }
        }
    }
}
