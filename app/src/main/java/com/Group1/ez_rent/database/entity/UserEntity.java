package com.Group1.ez_rent.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table",
        foreignKeys = @ForeignKey(
                entity = RoomEntity.class,
                parentColumns = "room_id",
                childColumns = "room_id",
                onDelete = ForeignKey.SET_NULL
        ))
public class UserEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    private String password;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "user_address")
    private String userAddress;
    private String role; // "NGUOI_THUE" or "KHACH"

    @ColumnInfo(name = "room_id")
    private String roomId; // Can be null for KHACH

    public UserEntity() {}

    public UserEntity(@NonNull String phoneNumber, String password, String fullName, 
                      String userAddress, String role, String roomId) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullName = fullName;
        this.userAddress = userAddress;
        this.role = role;
        this.roomId = roomId;
    }

    @NonNull
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(@NonNull String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
}
