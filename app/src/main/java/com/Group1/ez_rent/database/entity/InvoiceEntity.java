package com.Group1.ez_rent.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_table",
        foreignKeys = @ForeignKey(
                entity = RoomEntity.class,
                parentColumns = "room_id",
                childColumns = "room_id",
                onDelete = ForeignKey.CASCADE
        ))
public class InvoiceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "invoice_id")
    private int invoiceId;

    @ColumnInfo(name = "room_id")
    private String roomId;

    @ColumnInfo(name = "billing_month")
    private String billingMonth;

    @ColumnInfo(name = "old_electricity")
    private int oldElectricity;
    @ColumnInfo(name = "new_electricity")
    private int newElectricity;
    @ColumnInfo(name = "old_water")
    private int oldWater;
    @ColumnInfo(name = "new_water")
    private int newWater;

    @ColumnInfo(name = "total_amount")
    private double totalAmount;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "is_paid")
    private int isPaid; // 1: Paid, 0: Unpaid

    public InvoiceEntity() {}

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getBillingMonth() { return billingMonth; }
    public void setBillingMonth(String billingMonth) { this.billingMonth = billingMonth; }

    public int getOldElectricity() { return oldElectricity; }
    public void setOldElectricity(int oldElectricity) { this.oldElectricity = oldElectricity; }

    public int getNewElectricity() { return newElectricity; }
    public void setNewElectricity(int newElectricity) { this.newElectricity = newElectricity; }

    public int getOldWater() { return oldWater; }
    public void setOldWater(int oldWater) { this.oldWater = oldWater; }

    public int getNewWater() { return newWater; }
    public void setNewWater(int newWater) { this.newWater = newWater; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getIsPaid() { return isPaid; }
    public void setIsPaid(int isPaid) { this.isPaid = isPaid; }
}
