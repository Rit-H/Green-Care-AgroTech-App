package com.exampleee.agroapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.exampleee.agroapk.R;

public class AdminHomeActivity extends AppCompatActivity {


    private static final long BLINK_DURATION = 500;



    Button goToAddProductBtn, goToConsultBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        ImageView companyImageImageView = findViewById(R.id.decorative_image);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);



        goToAddProductBtn = findViewById(R.id.go_to_add_product_btn);
        goToConsultBtn = findViewById(R.id.consult);

        goToAddProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AddProductActivity.class));
            }
        });
        goToConsultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, AddConsultActivity.class));
            }
        });
    }


    private void startBlinkingAnimation(final ImageView textView) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            boolean isVisible = true;

            @Override
            public void run() {
                isVisible = !isVisible;
                textView.setVisibility(isVisible ? TextView.VISIBLE : TextView.INVISIBLE);
                handler.postDelayed(this, BLINK_DURATION);
            }
        }, BLINK_DURATION);
    }
}