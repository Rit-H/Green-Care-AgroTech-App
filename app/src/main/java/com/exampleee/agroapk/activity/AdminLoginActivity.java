package com.exampleee.agroapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exampleee.agroapk.R;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button admin_login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        usernameEditText = findViewById(R.id.admin_email);
        passwordEditText = findViewById(R.id.admin_password);
        admin_login_button = findViewById(R.id.admin_login_button);

        admin_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // Check if username and password are correct
                if (username.equals("greencare") && password.equals("greencare123")) {
                    // Successful login, redirect to admin home
                    startActivity(new Intent(AdminLoginActivity.this, AdminHomeActivity.class));
                    finish(); // Close login activity
                } else {
                    // Show error message
                    Toast.makeText(AdminLoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
