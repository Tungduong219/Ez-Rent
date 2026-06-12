package com.Group1.ez_rent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointment_table",
        foreignKeys = {
                @ForeignKey(entity = UserEntity.class, parentColumns = "phone_number", childColumns = "phone_number", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = RoomEntity.class, parentColumns = "room_id", childColumns = "room_id", onDelete = ForeignKey.CASCADE)
        })
public class AppointmentEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "appointment_id")
    private int appointmentId;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    @ColumnInfo(name = "room_id")
    private String roomId;

    @ColumnInfo(name = "appointment_time")
    private String appointmentTime;

    private String notes;
    private String status; // "CHUA_XEM", "DA_XEM", "DA_HUY"

    public AppointmentEntity() {}

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
