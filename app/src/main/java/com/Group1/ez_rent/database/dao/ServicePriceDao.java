package com.Group1.ez_rent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.Group1.ez_rent.database.entity.ServicePriceEntity;

import java.util.List;

@Dao
public interface ServicePriceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertServicePrice(ServicePriceEntity servicePrice);

    @Update
    void updateServicePrice(ServicePriceEntity servicePrice);

    @Delete
    void deleteServicePrice(ServicePriceEntity servicePrice);

    @Query("SELECT * FROM service_price_table")
    List<ServicePriceEntity> getAllServicePrices();

    @Query("SELECT * FROM service_price_table WHERE config_id = :id")
    ServicePriceEntity getServicePriceById(String id);
}
