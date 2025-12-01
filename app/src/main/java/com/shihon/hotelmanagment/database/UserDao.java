package com.shihon.hotelmanagment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE username = :u AND password = :p LIMIT 1")
    UserEntity getUserByCredentials(String u, String p);

    @Insert
    void insertUser(UserEntity user);

    @Query("SELECT COUNT(*) FROM users")
    int countUsers();
}
