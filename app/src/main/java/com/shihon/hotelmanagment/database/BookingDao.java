package com.shihon.hotelmanagment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDao {

    @Insert
    void insertBooking(BookingEntity booking);

    @Query("SELECT * FROM bookings")
    List<BookingEntity> getAllBookings();
}
