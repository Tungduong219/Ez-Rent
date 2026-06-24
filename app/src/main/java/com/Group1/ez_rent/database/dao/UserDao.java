package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Query("SELECT * FROM user_table")
    List<UserEntity> getAllUsers();

    @Query("SELECT * FROM user_table WHERE phone_number = :phone LIMIT 1")
    UserEntity getUserByPhone(String phone);
}
