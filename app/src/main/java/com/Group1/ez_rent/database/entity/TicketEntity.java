package com.Group1.ez_rent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ticket_table",
        foreignKeys = {
                @ForeignKey(entity = RoomEntity.class, parentColumns = "room_id", childColumns = "room_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = UserEntity.class, parentColumns = "phone_number", childColumns = "phone_number", onDelete = ForeignKey.CASCADE)
        })
public class TicketEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ticket_id")
    private int ticketId;

    @ColumnInfo(name = "room_id")
    private String roomId;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    private String title;
    private String description;
    private String priority; // "KHAN_CAP" / "BINH_THUONG"

    @ColumnInfo(name = "image_error_path")
    private String imageErrorPath;

    private String status; // "CHUA_XU_LY", "DANG_SUA", "HOAN_THANH"

    public TicketEntity() {}

    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getImageErrorPath() { return imageErrorPath; }
    public void setImageErrorPath(String imageErrorPath) { this.imageErrorPath = imageErrorPath; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
