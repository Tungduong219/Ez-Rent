package com.Group1.ez_rent.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "service_price_table")
public class ServicePriceEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "config_id")
    private String configId;

    @ColumnInfo(name = "config_name")
    private String configName;

    @ColumnInfo(name = "electricity_price")
    private double electricityPrice;

    @ColumnInfo(name = "water_price")
    private double waterPrice;

    @ColumnInfo(name = "internet_fee")
    private double internetFee;

    @ColumnInfo(name = "service_fee")
    private double serviceFee;

    public ServicePriceEntity() {}

    public ServicePriceEntity(@NonNull String configId, String configName, double electricityPrice, 
                              double waterPrice, double internetFee, double serviceFee) {
        this.configId = configId;
        this.configName = configName;
        this.electricityPrice = electricityPrice;
        this.waterPrice = waterPrice;
        this.internetFee = internetFee;
        this.serviceFee = serviceFee;
    }

    @NonNull
    public String getConfigId() { return configId; }
    public void setConfigId(@NonNull String configId) { this.configId = configId; }

    public String getConfigName() { return configName; }
    public void setConfigName(String configName) { this.configName = configName; }

    public double getElectricityPrice() { return electricityPrice; }
    public void setElectricityPrice(double electricityPrice) { this.electricityPrice = electricityPrice; }

    public double getWaterPrice() { return waterPrice; }
    public void setWaterPrice(double waterPrice) { this.waterPrice = waterPrice; }

    public double getInternetFee() { return internetFee; }
    public void setInternetFee(double internetFee) { this.internetFee = internetFee; }

    public double getServiceFee() { return serviceFee; }
    public void setServiceFee(double serviceFee) { this.serviceFee = serviceFee; }
}
