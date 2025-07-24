package com.exampleee.agroapk.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.exampleee.agroapk.MainActivity;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

    }

    public void SignIn(View view){

        // startActivities(new Intent[]{new Intent(LoginActivity.this, MainActivity.class)});
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){

            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length()<6) {
            Toast.makeText(this, "Password is to short,enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                            startActivities(new Intent[]{new Intent(LoginActivity.this, MainActivity.class)});
//                        } else {
//                            // Check if there's an exception and handle it
//                            if (task.getException() != null) {
//                                String errorMessage = task.getException().getMessage();
//                                if (errorMessage.contains("password") || errorMessage.contains("email")) {
//                                    // Display error message for wrong email/password
//                                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    // Display generic error message
//                                    Toast.makeText(LoginActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
                        // Inside onComplete method
                        if (!task.isSuccessful()) {
                            Exception exception = task.getException();
                            if (exception instanceof FirebaseAuthInvalidUserException) {
                                // Invalid user (email not registered)
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                // Invalid password
                                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            } else {
                                // Generic error message
                                Toast.makeText(LoginActivity.this, "Authentication failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivities(new Intent[]{new Intent(LoginActivity.this, MainActivity.class)});

                        }else {

                            Toast.makeText(LoginActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    public void Signup(View view) {

        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));


    }


    public void resetPassword(View view) {
        String userEmail = email.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter your registered email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(userEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null) {
            startActivities(new Intent[]{new Intent(LoginActivity.this, MainActivity.class)});

        }
    }
}