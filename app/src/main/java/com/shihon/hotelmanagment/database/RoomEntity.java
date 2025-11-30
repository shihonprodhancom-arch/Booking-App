package com.shihon.hotelmanagment.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rooms")
public class RoomEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String roomNumber;
    public String roomType;
    public double price;
    public boolean isBooked;

    public RoomEntity(String roomNumber, String roomType, double price, boolean isBooked) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = isBooked;
    }
}
