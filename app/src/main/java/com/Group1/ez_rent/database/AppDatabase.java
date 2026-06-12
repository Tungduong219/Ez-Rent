package com.Group1.ez_rent.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.Group1.ez_rent.database.dao.*;
import com.Group1.ez_rent.database.entity.*;

@Database(entities = {
        ServicePriceEntity.class, 
        RoomEntity.class, 
        UserEntity.class, 
        InvoiceEntity.class, 
        TicketEntity.class, 
        AppointmentEntity.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "ezrent_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ServicePriceDao servicePriceDao();
    public abstract RoomDao roomDao();
    public abstract UserDao userDao();
    public abstract InvoiceDao invoiceDao();
    public abstract TicketDao ticketDao();
    public abstract AppointmentDao appointmentDao();
}
