package com.shihon.hotelmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
        setSupportActionBar(toolbar);

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

        // ---------------- Role-based visibility ----------------
        String role = getIntent().getStringExtra("userRole"); // LoginActivity থেকে পাঠানো role

        if (role != null) {
            role = role.toLowerCase();
            Menu menu = navigationView.getMenu();

            switch (role) {
                case "admin":
                    btnRooms.setVisibility(View.VISIBLE);
                    btnBookings.setVisibility(View.VISIBLE);
                    btnAddRoom.setVisibility(View.VISIBLE);
                    btnAddBooking.setVisibility(View.VISIBLE);

                    menu.findItem(R.id.nav_rooms).setVisible(true);
                    menu.findItem(R.id.nav_bookings).setVisible(true);
                    menu.findItem(R.id.nav_add_room).setVisible(true);
                    menu.findItem(R.id.nav_add_booking).setVisible(true);
                    break;

                case "manager":
                    btnRooms.setVisibility(View.VISIBLE);
                    btnBookings.setVisibility(View.VISIBLE);
                    btnAddRoom.setVisibility(View.GONE);
                    btnAddBooking.setVisibility(View.GONE);

                    menu.findItem(R.id.nav_rooms).setVisible(true);
                    menu.findItem(R.id.nav_bookings).setVisible(true);
                    menu.findItem(R.id.nav_add_room).setVisible(false);
                    menu.findItem(R.id.nav_add_booking).setVisible(false);
                    break;

                case "reception":
                    btnRooms.setVisibility(View.VISIBLE);
                    btnBookings.setVisibility(View.VISIBLE);
                    btnAddRoom.setVisibility(View.GONE);
                    btnAddBooking.setVisibility(View.VISIBLE);

                    menu.findItem(R.id.nav_rooms).setVisible(true);
                    menu.findItem(R.id.nav_bookings).setVisible(true);
                    menu.findItem(R.id.nav_add_room).setVisible(false);
                    menu.findItem(R.id.nav_add_booking).setVisible(true);
                    break;
            }
        }

        // ---------------- CardView Click Listeners ----------------
        btnRooms.setOnClickListener(v -> openActivity(RoomListActivity.class));
        btnBookings.setOnClickListener(v -> openActivity(BookingListActivity.class));
        btnAddRoom.setOnClickListener(v -> openActivity(RoomActivity.class));
        btnAddBooking.setOnClickListener(v -> openActivity(AddBookingActivity.class));

        // ---------------- Drawer Menu Click Listener ----------------
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
        intent.putExtra("userRole", getIntent().getStringExtra("userRole")); // role পাঠানো
        startActivity(intent);
    }
}
