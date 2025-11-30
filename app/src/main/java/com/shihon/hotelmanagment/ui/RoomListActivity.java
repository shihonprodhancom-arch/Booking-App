package com.shihon.hotelmanagment.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.adapters.RoomAdapter;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.RoomEntity;

import java.util.List;

public class RoomListActivity extends AppCompatActivity {

    RecyclerView rvRooms;
    RoomAdapter adapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        rvRooms = findViewById(R.id.rvRooms);
        rvRooms.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        List<RoomEntity> rooms = db.roomDao().getAllRooms(); // সব রুম
        adapter = new RoomAdapter(rooms);
        rvRooms.setAdapter(adapter);
    }
}
