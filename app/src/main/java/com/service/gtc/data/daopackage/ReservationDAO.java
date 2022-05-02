package com.service.gtc.data.daopackage;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.service.gtc.model.database.ReservationData;

import java.util.List;

public interface ReservationDAO {
    @Insert
    void insert(ReservationData reservation);

    @Insert
    void update(ReservationData reservation);

    @Insert
    void delete(ReservationData reservation);

    @Query("SELECT * FROM reservationData WHERE reservationId = :reservationId")
    LiveData<ReservationData> getReservation(String reservationId);

    @Query("SELECT * FROM reservationData ORDER BY reservationId DESC")
    LiveData<List<ReservationData>> getAllReservations();

    @Query("DELETE FROM reservationData WHERE reservationId= :reservationId")
    void deleteById(int reservationId);
}
