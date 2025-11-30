package com.shihon.hotelmanagment.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookings")
public class BookingEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String guestName;
    public String roomNumber;
    public String checkInDate;
    public String checkOutDate;
    public double totalPrice;
    public String paymentMethod;

    public BookingEntity(String guestName, String roomNumber, String checkInDate,
                         String checkOutDate, double totalPrice, String paymentMethod) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
}
