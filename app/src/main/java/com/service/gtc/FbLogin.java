package com.service.gtc;


import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.Arrays;
import androidx.annotation.NonNull;

import android.util.Log;

import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;


public class FbLogin extends Login {
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    ProgressDialog fbprogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fbprogressDialog = new ProgressDialog(this);
        fbprogressDialog.setMessage("Facebook Sign In");
        fbprogressDialog.show();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));




        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(FbLogin.this, "Something went wrong." , Toast.LENGTH_SHORT).show();
                        fbprogressDialog.dismiss();
                        finish();
                    }
                });


    }
    //should the onActivity Result should be protected or public? and why?
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            fbprogressDialog.dismiss();
                        } else {

                            // If sign in fails, display a message to the user.

                            Toast.makeText(FbLogin.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                            fbprogressDialog.dismiss();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent=new Intent(FbLogin.this,Account.class);
        startActivity(intent);
    }


}