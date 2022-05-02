package com.service.gtc.data.daopackage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.service.gtc.model.database.RegisterData;

import java.util.List;

@Dao
public interface RegisterDAO {
    @Insert
    void insert(RegisterData register);

    @Update
    void update(RegisterData register);

    @Delete
    void delete(RegisterData register);

    @Query("SELECT * FROM registerData")
    LiveData<List<RegisterData>> getAllData();
}
