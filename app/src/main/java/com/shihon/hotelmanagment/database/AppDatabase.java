package com.shihon.hotelmanagment.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {UserEntity.class, RoomEntity.class, BookingEntity.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public abstract UserDao userDao();
    public abstract RoomDao roomDao();
    public abstract BookingDao bookingDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "hotel_database")
                    .fallbackToDestructiveMigration() // পুরোনো schema change হলে DB reset হবে
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // Pre-populate default users
                            Executors.newSingleThreadExecutor().execute(() -> {
                                UserDao userDao = instance.userDao();
                                if (userDao.countUsers() == 0) {
                                    userDao.insertUser(new UserEntity("admin", "admin123", "admin"));
                                    userDao.insertUser(new UserEntity("manager", "manager123", "manager"));
                                    userDao.insertUser(new UserEntity("reception", "recep123", "reception"));
                                }
                            });
                        }
                    })
                    .allowMainThreadQueries() // ছোট প্রজেক্টের জন্য, বড় অ্যাপে Async ব্যবহার করা উচিত
                    .build();
        }
        return instance;
    }
}
