package com.service.gtc.data.repositorypackage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.service.gtc.data.daopackage.GTCDatabase;
import com.service.gtc.data.daopackage.RegisterDAO;
import com.service.gtc.model.database.RegisterData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterRepository {

    private static RegisterRepository instance;
    private RegisterDAO registerDAO;
    private ExecutorService executorService;
    private LiveData<List<RegisterData>> allAccounts;

    private RegisterRepository(Application application){
        registerDAO = GTCDatabase.getInstance(application).registerDAO();
        allAccounts = registerDAO.getAllData();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static RegisterRepository getInstance(Application application)
    {
        if(instance == null) {
            instance = new RegisterRepository(application);
        }
        return instance;
    }

    public LiveData<List<RegisterData>> getAllAccounts(){
        allAccounts = registerDAO.getAllData();
        return allAccounts;
    }



    public void updateAccount(RegisterData registerData)
    {
        executorService.execute(() -> registerDAO.update(registerData));
    }

    public void deleteAccountById(int account_id)
    {
        executorService.execute(() -> registerDAO.deleteById(account_id));
    }
}
