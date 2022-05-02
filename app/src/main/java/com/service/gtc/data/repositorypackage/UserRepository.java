package com.service.gtc.data.repositorypackage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;
import com.service.gtc.model.UserData;

public class UserRepository {

    private static UserRepository instance;
    private final Application application;
    private UserData currentUser;

    public UserRepository(Application application) {
        this.application = application;
        currentUser = new UserData();
    }

    public static UserRepository getInstance(Application application) {
        if(instance == null) {
            instance = new UserRepository(application);
        }

        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return currentUser;
    }

    public void signOut(){
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }
}
