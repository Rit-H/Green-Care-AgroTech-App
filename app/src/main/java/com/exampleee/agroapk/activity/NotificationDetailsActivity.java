package com.exampleee.agroapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Notification;

public class NotificationDetailsActivity extends AppCompatActivity {

    Notification notification = Notification.notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);

        if (notification == null) {
            Toast.makeText(this, "No Notification Found...", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}