package com.shihon.hotelmanagment.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.UserDao;
import com.shihon.hotelmanagment.database.UserEntity;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    Button btnChangePassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePasswordSubmit);

        String username = getIntent().getStringExtra("username");
        UserDao userDao = AppDatabase.getInstance(this).userDao();

        btnChangePassword.setOnClickListener(v -> {
            String oldPass = edtOldPassword.getText().toString();
            String newPass = edtNewPassword.getText().toString();
            String confirmPass = edtConfirmPassword.getText().toString();

            UserEntity user = userDao.getUserByUsername(username);
            if (user == null) {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!user.getPassword().equals(oldPass)) {
                Toast.makeText(this, "Old password is incorrect", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "New password does not match", Toast.LENGTH_SHORT).show();
                return;
            }

            user.setPassword(newPass);
            userDao.updateUser(user);
            Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
