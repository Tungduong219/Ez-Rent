package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.AppointmentEntity;

import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppointment(AppointmentEntity appointment);

    @Update
    void updateAppointment(AppointmentEntity appointment);

    @Delete
    void deleteAppointment(AppointmentEntity appointment);

    @Query("SELECT * FROM appointment_table")
    List<AppointmentEntity> getAllAppointments();

    @Query("SELECT * FROM appointment_table WHERE phone_number = :phone")
    List<AppointmentEntity> getAppointmentsByUser(String phone);
}
