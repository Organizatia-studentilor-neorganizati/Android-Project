package com.service.gtc.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.service.gtc.data.repositorypackage.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public MainActivityViewModel(@NonNull Application application){
        super(application);
        userRepository = UserRepository.getInstance(application);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public void signOut(){
        userRepository.signOut();
    }
}
