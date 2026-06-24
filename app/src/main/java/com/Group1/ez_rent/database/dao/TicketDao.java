package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.TicketEntity;

import java.util.List;

@Dao
public interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTicket(TicketEntity ticket);

    @Update
    void updateTicket(TicketEntity ticket);

    @Delete
    void deleteTicket(TicketEntity ticket);

    @Query("SELECT * FROM ticket_table")
    List<TicketEntity> getAllTickets();

    @Query("SELECT * FROM ticket_table WHERE room_id = :roomId")
    List<TicketEntity> getTicketsByRoom(String roomId);
}
