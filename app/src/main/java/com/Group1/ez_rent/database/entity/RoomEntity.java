package com.Group1.ez_rent.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_table",
        foreignKeys = @ForeignKey(
                entity = ServicePriceEntity.class,
                parentColumns = "config_id",
                childColumns = "config_id",
                onDelete = ForeignKey.RESTRICT
        ))
public class RoomEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "room_id")
    private String roomId;

    @ColumnInfo(name = "room_name")
    private String roomName;

    private double price;
    private double area;
    private String district;
    private String description;

    @ColumnInfo(name = "config_id")
    private String configId;

    public RoomEntity() {}

    public RoomEntity(@NonNull String roomId, String roomName, double price, double area, 
                      String district, String description, String configId) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.price = price;
        this.area = area;
        this.district = district;
        this.description = description;
        this.configId = configId;
    }

    @NonNull
    public String getRoomId() { return roomId; }
    public void setRoomId(@NonNull String roomId) { this.roomId = roomId; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getConfigId() { return configId; }
    public void setConfigId(String configId) { this.configId = configId; }
}
