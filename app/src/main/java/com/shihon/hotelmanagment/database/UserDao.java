package com.shihon.hotelmanagment.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    UserEntity getUserByUsername(String username);

    @Query("SELECT COUNT(*) FROM users")
    int countUsers();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    UserEntity getUserByCredentials(String username, String password);

    @Query("SELECT * FROM users")
    List<UserEntity> getAllUsers();
}
