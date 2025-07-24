package com.exampleee.agroapk.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//
//import com.example.agroapk.R;
//
//public class Address_map_activity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_address_map);
//
//        ImageView companyImageImageView = findViewById(R.id.addressimage);
//
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
//
//
//// Apply animation to image view
//        companyImageImageView.startAnimation(animation);
//    }
//}


//package com.example.agroapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.exampleee.agroapk.R;

public class Address_map_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_map);

        ImageView companyImageImageView = findViewById(R.id.addressimage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        companyImageImageView.startAnimation(animation);




        // Find the TextView displaying the address
        TextView addressTextView = findViewById(R.id.item_address_address);

        // Set click listener to open Google Maps with the address
        addressTextView.setOnClickListener(v -> {
            String address = "Green care agro tech,Vadala , Shrirampur-Newasa Rd, Maharashtra 413739, India";
            //String address ="Vadala nibhani road, near manali garden, Shrirampur, Maharashtra 413739";

            openGoogleMaps(address);
        });

        companyImageImageView = findViewById(R.id.addressimage);

        // Set click listener to open Google Maps with the address
        companyImageImageView.setOnClickListener(v -> {
            String address = "Green care agro tech,Vadala nibhani road ,near manali garden, Shrirampur, Maharashtra 413739, India";
            //String address ="Vadala nibhani road, near manali garden, Shrirampur, Maharashtra 413739";

            openGoogleMaps(address);
        });
    }

    // Method to open Google Maps with the specified address
    private void openGoogleMaps(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            // If Google Maps app is not available, open Google Maps website
            String mapsUrl = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(address);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl));
            startActivity(webIntent);
        }
    }
}
