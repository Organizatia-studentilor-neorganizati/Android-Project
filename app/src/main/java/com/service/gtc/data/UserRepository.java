package com.service.gtc.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.auth.AuthUI;
import com.service.gtc.model.UserData;

public class UserRepository {

    private static UserRepository instance;
    private final Application application;
    private UserData currentUser;

    public UserRepository(Application application, UserData currentUser) {
        this.application = application;
        currentUser = new UserData();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return currentUser;
    }

    public void signOut(){
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }
}
