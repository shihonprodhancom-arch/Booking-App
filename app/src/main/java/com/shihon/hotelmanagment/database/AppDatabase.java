package com.shihon.hotelmanagment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomEntity.class, BookingEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract RoomDao roomDao();
    public abstract BookingDao bookingDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "hotel_database")
                    .allowMainThreadQueries() // ছোট প্রজেক্টের জন্য, বড় অ্যাপে Async ব্যবহার করা উচিত
                    .build();
        }
        return instance;
    }
}
