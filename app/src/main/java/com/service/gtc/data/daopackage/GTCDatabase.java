package com.service.gtc.data.daopackage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.service.gtc.model.database.RegisterData;
import com.service.gtc.model.database.ReservationData;

@Database(entities = {RegisterData.class, ReservationData.class}, version = 2)
public abstract class GTCDatabase extends RoomDatabase {

    private static GTCDatabase instance;
    public abstract RegisterDAO registerDAO();
    public abstract ReservationDAO reservationDAO();

   public static synchronized GTCDatabase getInstance(Context context){
       if(instance == null){
           instance = Room.databaseBuilder(context.getApplicationContext(),
           GTCDatabase.class, "gtc_database")
           .fallbackToDestructiveMigration().build();
       }
       return instance;
   }
}
