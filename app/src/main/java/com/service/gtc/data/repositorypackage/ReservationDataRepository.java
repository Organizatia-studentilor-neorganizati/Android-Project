package com.service.gtc.data.repositorypackage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.service.gtc.data.daopackage.GTCDatabase;
import com.service.gtc.data.daopackage.ReservationDAO;
import com.service.gtc.model.database.ReservationData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReservationDataRepository {

    private static ReservationDataRepository instance;
    private ReservationDAO reservationDAO;
    private ExecutorService executorService;
    private LiveData<List<ReservationData>> allReservations;

    private ReservationDataRepository(Application application) {
        reservationDAO = GTCDatabase.getInstance(application).reservationDAO();
        allReservations = reservationDAO.getAllReservations();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static ReservationDataRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ReservationDataRepository(application);
        }
        return instance;

    }

    public void addReservationData(ReservationData reservationData) {
        executorService.execute(() -> reservationDAO.insert(reservationData));
    }

    public LiveData<List<ReservationData>> getReservationData() {
        allReservations = reservationDAO.getAllReservations();
        return allReservations;
    }

    public void updateReservationData(ReservationData reservationData){
    executorService.execute(() -> reservationDAO.update(reservationData));}

    public void deleteReservationData(ReservationData reservationData)
    {executorService.execute(() -> reservationDAO.delete(reservationData));}
}




