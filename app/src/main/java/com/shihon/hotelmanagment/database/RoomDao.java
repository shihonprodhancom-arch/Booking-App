package com.shihon.hotelmanagment.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert
    void insert(RoomEntity room);

    @Update
    void update(RoomEntity room);

    @Delete
    void delete(RoomEntity room);

    @Query("SELECT * FROM rooms")
    List<RoomEntity> getAllRooms();

    @Query("SELECT * FROM rooms WHERE isBooked = 0")
    List<RoomEntity> getAvailableRooms();
}
