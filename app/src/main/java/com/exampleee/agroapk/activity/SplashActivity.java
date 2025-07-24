package com.exampleee.agroapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.exampleee.agroapk.MainActivity;
import com.exampleee.agroapk.R;


public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView companyImageImageView = findViewById(R.id.imageViewLogo);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Check if the user is logged in
                if (SessionManager.getInstance(SplashActivity.this).isLoggedIn()) {
                    // If user is logged in, start MainActivity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    // If user is not logged in, start LoginActivity
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                // Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
