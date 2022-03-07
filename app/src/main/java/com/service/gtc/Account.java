package com.service.gtc;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity {
    TextView verifyTxt;
    FirebaseAuth fAuth;
    Button verifyBtn, logoutBtn, resetpassBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        verifyTxt = findViewById(R.id.verifytxt);
        verifyBtn = findViewById(R.id.verifybtn);
        logoutBtn = findViewById(R.id.logoutbtn);
        resetpassBtn = findViewById(R.id.resetpassbtn);
        FirebaseUser user;

        fAuth = FirebaseAuth.getInstance();
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        /*
        I want to create a method in which if an user has been loged with gaccount to send his data to Firestore
        BUT FIRESTORE AIN T WORKING NOW CUZ I AM DUMB*/

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
        }

        user = fAuth.getCurrentUser();

        if (!user.isEmailVerified()) {
            verifyTxt.setVisibility(View.VISIBLE);
            verifyBtn.setVisibility(View.VISIBLE);

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Account.this, "A verifcation email has been sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Email not sent to user" + e.getMessage());
                        }
                    });
                }
            });
        }
        if (user.isEmailVerified()) {
            verifyTxt.setVisibility(View.GONE);
            verifyBtn.setVisibility(View.GONE);
        }

        resetpassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText resetPassword = new EditText(view.getContext());

                AlertDialog.Builder passwordreesetdiag = new AlertDialog.Builder(view.getContext());

                passwordreesetdiag.setTitle("Password Reset");
                passwordreesetdiag.setMessage(" Enter the new password ");
                passwordreesetdiag.setView(resetPassword);
                passwordreesetdiag.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //take the email and send reset link
                        String newPassword = resetPassword.getText().toString();

                        user.updatePassword(newPassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Account.this, "Password has been reset", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Account.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


                });


                passwordreesetdiag.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                passwordreesetdiag.create().show();


            }
        });


    }


}