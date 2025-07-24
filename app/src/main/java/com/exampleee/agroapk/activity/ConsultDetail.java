package com.exampleee.agroapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Consult;

public class ConsultDetail extends AppCompatActivity {

    private static final long DELAY = 50; // Delay between each character appearance
    private static final long BLINK_DURATION = 500; // Duration of each blink cycle


    Consult consult= Consult.consult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ImageView companyImageImageView = findViewById(R.id.about_us_image);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);

        companyImageImageView.startAnimation(animation);


        // Populate TextViews with company information
        TextView companyNameTextView = findViewById(R.id.text_company_name);
        animateText("Green Care Agro Tech", companyNameTextView);

        TextView companyInfoTextView = findViewById(R.id.text_company_info);
        animateText("Green care agro tech was established in the year 2014, in Shrirampur. To enhance yield and promote sustainable production, we manufacture a variety of high-quality plant nutrition products suitable for all types of crops.\\n\\n\\These include Amino Acids Chelates, EDTA Chelates, Micronutrient Mixture Fertilizers, Certified Organic Micronutrients, Organic Fertilizers, Organic Acid Series, Water-Soluble Fertilizers, Natural Biostimulants, Spray Adjuvants, Plant Growth Regulators, Soil and Water Conditioners, Biofertilizers, Biofungicides, Biopesticides, and specific Crop-Specific Fertilizers.Both companies contribute to the production of these products. We operate in more than 5 states in India and are soon expanding to cover the entire nation. Additionally, our products are exported to countries like Nepal, Sri Lanka, Vietnam, and more.\\n\\nRecognized with numerous awards for excellence in agricultural input production, we are a leading force in manufacturing products for organic residue-free farming. ASPL proudly holds ISO 9001:2015 and 14001:2015 certifications.We trust that these products will empower you to achieve high-quality and exportable crop production on your farm.", companyInfoTextView);

        // Start continuous blinking animation for vision TextView
        TextView companydevlopTextView = findViewById(R.id.about_us_scoe);
        startBlinkingAnimation(companydevlopTextView);



        // Populate TextView with company vision
        TextView companyVisionDetailsTextView = findViewById(R.id.text_company_vision_details);
        animateText("Kissano ki pahili pasand", companyVisionDetailsTextView);

        if (consult == null) {
            Toast.makeText(this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            finish();
        }
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