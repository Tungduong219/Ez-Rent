package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.RoomEntity;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoom(RoomEntity room);

    @Update
    void updateRoom(RoomEntity room);

    @Delete
    void deleteRoom(RoomEntity room);

    @Query("SELECT * FROM room_table")
    List<RoomEntity> getAllRooms();

    @Query("SELECT * FROM room_table WHERE room_id = :id")
    RoomEntity getRoomById(String id);
}
