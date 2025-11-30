package com.shihon.hotelmanagment.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.RoomEntity;

public class RoomActivity extends AppCompatActivity {

    EditText etRoomNumber, etRoomType, etPrice;
    Button btnSaveRoom;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        etRoomNumber = findViewById(R.id.etRoomNumber);
        etRoomType = findViewById(R.id.etRoomType);
        etPrice = findViewById(R.id.etPrice);
        btnSaveRoom = findViewById(R.id.btnSaveRoom);

        db = AppDatabase.getInstance(this);

        btnSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etRoomNumber.getText().toString().trim();
                String type = etRoomType.getText().toString().trim();
                double price;

                try {
                    price = Double.parseDouble(etPrice.getText().toString().trim());
                } catch (Exception e) {
                    price = 0;
                }

                if (!number.isEmpty() && !type.isEmpty()) {
                    RoomEntity room = new RoomEntity(number, type, price, false);
                    db.roomDao().insert(room);
                    Toast.makeText(RoomActivity.this, "Room added!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RoomActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
