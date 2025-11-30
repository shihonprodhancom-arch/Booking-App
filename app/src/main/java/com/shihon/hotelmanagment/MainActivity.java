package com.shihon.hotelmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.shihon.hotelmanagment.ui.AddBookingActivity;
import com.shihon.hotelmanagment.ui.BookingListActivity;
import com.shihon.hotelmanagment.ui.RoomActivity;
import com.shihon.hotelmanagment.ui.RoomListActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private CardView btnRooms, btnBookings, btnAddRoom, btnAddBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------- TOOLBAR ----------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // MUST NEED THIS

        // ---------------- DRAWER ----------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // ---------------- CARDVIEW Buttons ----------------
        btnRooms = findViewById(R.id.btnRooms);
        btnBookings = findViewById(R.id.btnBookings);
        btnAddRoom = findViewById(R.id.btnAddRoom);
        btnAddBooking = findViewById(R.id.btnAddBooking);

        // Fix — XML এ IDs ঠিক আছে তাই এখানে আর Crash হবে না
        btnRooms.setOnClickListener(v -> openActivity(RoomListActivity.class));
        btnBookings.setOnClickListener(v -> openActivity(BookingListActivity.class));
        btnAddRoom.setOnClickListener(v -> openActivity(RoomActivity.class));
        btnAddBooking.setOnClickListener(v -> openActivity(AddBookingActivity.class));

        // ---------------- Drawer Menu Item Click ----------------
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_rooms) openActivity(RoomListActivity.class);
            else if (id == R.id.nav_bookings) openActivity(BookingListActivity.class);
            else if (id == R.id.nav_add_room) openActivity(RoomActivity.class);
            else if (id == R.id.nav_add_booking) openActivity(AddBookingActivity.class);

            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
