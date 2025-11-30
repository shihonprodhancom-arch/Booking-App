package com.shihon.hotelmanagment.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.BookingEntity;
import com.shihon.hotelmanagment.database.RoomEntity;

import java.util.List;

public class AddBookingActivity extends AppCompatActivity {

    EditText etGuestName, etCheckIn, etCheckOut, etTotalPrice;
    Spinner spRoom;
    Button btnSave;

    AppDatabase db;
    List<RoomEntity> availableRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        etGuestName = findViewById(R.id.etGuestName);
        etCheckIn = findViewById(R.id.etCheckIn);
        etCheckOut = findViewById(R.id.etCheckOut);
        etTotalPrice = findViewById(R.id.etTotalPrice);
        spRoom = findViewById(R.id.spRoom);
        btnSave = findViewById(R.id.btnSaveBooking);

        db = AppDatabase.getInstance(this);

        // Load available rooms into Spinner
        availableRooms = db.roomDao().getAvailableRooms();
        String[] roomNumbers = new String[availableRooms.size()];
        for (int i = 0; i < availableRooms.size(); i++) {
            roomNumbers[i] = availableRooms.get(i).roomNumber;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoom.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guestName = etGuestName.getText().toString().trim();
                String checkIn = etCheckIn.getText().toString().trim();
                String checkOut = etCheckOut.getText().toString().trim();
                double totalPrice;

                try {
                    totalPrice = Double.parseDouble(etTotalPrice.getText().toString().trim());
                } catch (Exception e) {
                    totalPrice = 0;
                }

                String selectedRoom = spRoom.getSelectedItem().toString();

                if (!guestName.isEmpty() && !checkIn.isEmpty() && !checkOut.isEmpty()) {
                    // Create BookingEntity using full constructor
                    BookingEntity booking = new BookingEntity(
                            guestName,
                            selectedRoom,
                            checkIn,
                            checkOut,
                            totalPrice,
                            "Pending" // payment method, উদাহরণস্বরূপ
                    );

                    db.bookingDao().insertBooking(booking);

                    // Update room as booked
                    for (RoomEntity r : availableRooms) {
                        if (r.roomNumber.equals(selectedRoom)) {
                            r.isBooked = true;
                            db.roomDao().update(r);
                            break;
                        }
                    }

                    Toast.makeText(AddBookingActivity.this, "Booking added!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddBookingActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
