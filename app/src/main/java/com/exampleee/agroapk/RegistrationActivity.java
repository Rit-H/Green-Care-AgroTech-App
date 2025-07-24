package com.exampleee.agroapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText name, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void Signup(View view) {
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();
        String userEmail = email.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6) {
            Toast.makeText(this, "Password is too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(RegistrationActivity.this, "Email is already in use", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void SignIn(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}


//
//// RegistrationActivity.java
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.agroapk.activity.LoginActivity;
//import com.example.agroapk.activity.Waiting_Activity;
//
//import com.google.firebase.auth.FirebaseAuth;
//
//public class RegistrationActivity extends AppCompatActivity {
//    EditText name, email, password;
//    private FirebaseAuth auth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registration);
//        auth = FirebaseAuth.getInstance();
//        name = findViewById(R.id.name);
//        email = findViewById(R.id.email);
//        password = findViewById(R.id.password);
//    }
//
//    public void Signup(View view) {
//        String userName = name.getText().toString().trim();
//        String userPassword = password.getText().toString().trim();
//        String userEmail = email.getText().toString().trim();
//
//        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
//            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (userPassword.length() < 6) {
//            Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Start the waiting activity
//        startActivity(new Intent(RegistrationActivity.this, Waiting_Activity.class));
//
//        auth.createUserWithEmailAndPassword(userEmail, userPassword)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Registration successful, send verification email
//                        sendVerificationEmail();
//                    } else {
//                        // Registration failed
//                        Toast.makeText(RegistrationActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    private void sendVerificationEmail() {
//        auth.getCurrentUser().sendEmailVerification()
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        // Verification email sent successfully
//                        Toast.makeText(RegistrationActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // Failed to send verification email
//                        Toast.makeText(RegistrationActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void SignIn(View view) {
//        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
//    }
//}


