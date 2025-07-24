package com.exampleee.agroapk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.exampleee.agroapk.R;

public class Review_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


// Animation for WebView


        ImageView reviewImage = findViewById(R.id.review_image1);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        reviewImage.startAnimation(animation);

        ImageView reviewImage2 = findViewById(R.id.review_image2);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        reviewImage2.startAnimation(animation2);


// Animation for review_image3
        ImageView reviewImage3 = findViewById(R.id.review_image3);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        reviewImage3.startAnimation(animation3);


// Animation for review_image3
        ImageView reviewImage4 = findViewById(R.id.review_image4);
        Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        reviewImage4.startAnimation(animation4);

        TextView reviewImage5 = findViewById(R.id.textViewReviewTitle);
        Animation animation5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        reviewImage5.startAnimation(animation5);






        WebView webView=findViewById(R.id.webview);

        Animation animationWebView = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_top);
        webView.startAnimation(animationWebView);

        String vedio="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/xwqiy-y1R24?si=cPbhsUWjS1xCAG1M\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";

        //String vedio ="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OpLaeubVeOQ?si=tU50Df4jYNPnuzXb\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(vedio,"text/html","utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
    }
}