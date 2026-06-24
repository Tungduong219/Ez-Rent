package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.InvoiceEntity;

import java.util.List;

@Dao
public interface InvoiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInvoice(InvoiceEntity invoice);

    @Update
    void updateInvoice(InvoiceEntity invoice);

    @Delete
    void deleteInvoice(InvoiceEntity invoice);

    @Query("SELECT * FROM invoice_table")
    List<InvoiceEntity> getAllInvoices();

    @Query("SELECT * FROM invoice_table WHERE room_id = :roomId")
    List<InvoiceEntity> getInvoicesByRoom(String roomId);
}
