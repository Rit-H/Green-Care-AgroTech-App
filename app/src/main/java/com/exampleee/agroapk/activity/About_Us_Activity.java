package com.exampleee.agroapk.activity;

//https://sites.google.com/view/green-care-agrotech/home

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.exampleee.agroapk.R;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class About_Us_Activity extends AppCompatActivity {

    private static final long DELAY = 50; // Delay between each character appearance
    private static final long BLINK_DURATION = 500; // Duration of each blink cycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ImageView companyImageImageView = findViewById(R.id.about_us_image);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);


        // Populate TextViews with company information
        TextView companyNameTextView = findViewById(R.id.text_company_name);
        animateText("Green Care Agro Tech", companyNameTextView);

        TextView companyInfoTextView = findViewById(R.id.text_company_info);
        animateText("Recognized with numerous awards for excellence in agricultural input production, we are a leading force in manufacturing products for organic residue-free farming. ASPL proudly holds ISO 9001:2015 and 14001:2015 certifications.We trust that these products will empower you to achieve high-quality and exportable crop production on your farm.", companyInfoTextView);

        // Start continuous blinking animation for vision TextView
           TextView companydevlopTextView = findViewById(R.id.about_us_scoe);
           startBlinkingAnimation(companydevlopTextView);



        // Populate TextView with company vision
        TextView companyVisionDetailsTextView = findViewById(R.id.text_company_vision_details);
        animateText("Kissano ki pahili pasand", companyVisionDetailsTextView);
    }

    private void animateText(final String text, final TextView textView) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index < text.length()) {
                    textView.append(String.valueOf(text.charAt(index++)));
                    handler.postDelayed(this, DELAY);
                }
            }
        }, DELAY);
    }

    private void startBlinkingAnimation(final TextView textView) {
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
