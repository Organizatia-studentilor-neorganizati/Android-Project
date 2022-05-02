package com.service.gtc.userinterface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.gtc.R;
import com.firebase.ui.auth.AuthUI;
import com.service.gtc.viewmodels.LoginViewModel;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_OK)
                    switchToMainActivity();
                else
                    Toast.makeText(this, "SIGN IN DID NOT SUCCED", Toast.LENGTH_SHORT).show();
            });

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loggedInCheck();

        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginbtn);
        loginButton.setOnClickListener(this::signIn);
    }

    private void loggedInCheck(){
        loginViewModel.getCurrentUser().observe(this, user -> {
            if(user != null)
                switchToMainActivity();
        });
    }

    private void switchToMainActivity(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void signIn(View v){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();

        activityResultLauncher.launch(signInIntent);
    }
}
