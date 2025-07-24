////package com.example.agroapk;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.cardview.widget.CardView;
////import androidx.fragment.app.Fragment;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.MenuItem;
////import android.widget.ImageView;
////
////import com.example.agroapk.activity.About_Us_Activity;
////import com.example.agroapk.activity.CartsActivety;
////import com.example.agroapk.activity.NotificationDetailsActivity;
////import com.example.agroapk.frags.CategoryFragment;
////import com.example.agroapk.frags.DiscussFragment;
////import com.example.agroapk.frags.HomeFragment;
////import com.example.agroapk.frags.NotificationFragment;
////import com.example.agroapk.frags.ProfileFragment;
////import com.google.android.material.bottomnavigation.BottomNavigationView;
////import com.google.android.material.navigation.NavigationBarView;
////
////public class MainActivity extends AppCompatActivity {
////
////
////    CardView btnCart;
////    ImageView companyprofile;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        btnCart = findViewById(R.id.btn_cart);
////        companyprofile=findViewById(R.id.profile_company_logo);
////
////
////
////
////        setFooter();
////
////
////
////
////        btnCart.setOnClickListener(v -> {
////            startActivity(new Intent(MainActivity.this, NotificationDetailsActivity.class));
////        });
////
////        companyprofile.setOnClickListener(v -> {
////            startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
////        });
////
////
////    }
////
////
////    private void setFooter() {
////
////        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment(MainActivity.this)).commit();
////        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
////
////        bottomNavigationView.setOnItemSelectedListener(item ->  {
////
////
////            Fragment fragment = new HomeFragment(MainActivity.this);
////
////
////            int item_id = item.getItemId();
////            if (item_id == R.id.bottom_home) {
////                fragment = new HomeFragment(MainActivity.this);
////
////            }else if  (item_id == R.id.bottom_category){
////                fragment = new CategoryFragment(MainActivity.this);
////
////            }else if  (item_id == R.id.profile_action_About_US) {
////                // fragment = new NotificationFragment(MainActivity.this);
////
////
////                // Start the AboutUsActivity
////                startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
////                // Return false to prevent the item from being selected
////                return false;
////
////
////                //Discuss= consult
////            }else if  (item_id == R.id.bottom_Consult) {
////                fragment = new DiscussFragment(MainActivity.this);
////
////            }else if  (item_id == R.id.bottom_Profile) {
////                fragment = new ProfileFragment(MainActivity.this);
////
////            }
////
////            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();
////
////
////
////            return true;
////
////        });
////
////    }
////}
//
//package com.example.agroapk;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.ImageView;
//
//import com.example.agroapk.activity.About_Us_Activity;
//import com.example.agroapk.activity.NotificationDetailsActivity;
//import com.example.agroapk.frags.CategoryFragment;
//import com.example.agroapk.frags.DiscussFragment;
//import com.example.agroapk.frags.HomeFragment;
//import com.example.agroapk.frags.ProfileFragment;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class MainActivity extends AppCompatActivity {
//
//    ImageView companyprofile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        companyprofile=findViewById(R.id.profile_company_logo);
//
//        setFooter();
//
//        companyprofile.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
//        });
//    }
//
//    private void setFooter() {
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new HomeFragment(MainActivity.this)).commit();
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
//
//        bottomNavigationView.setOnItemSelectedListener(item ->  {
//            Fragment fragment = null;
//
//            int item_id = item.getItemId();
//            if (item_id == R.id.bottom_home) {
//                fragment = new HomeFragment(MainActivity.this);
//            } else if  (item_id == R.id.bottom_category){
//                fragment = new CategoryFragment(MainActivity.this);
//            } else if  (item_id == R.id.profile_action_About_US) {
//                startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
//                return false;
//            } else if  (item_id == R.id.bottom_Consult) {
//                fragment = new DiscussFragment(MainActivity.this);
//            } else if  (item_id == R.id.bottom_Profile) {
//                fragment = new ProfileFragment(MainActivity.this);
//            }
//
//            if (fragment != null) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).commit();
//            }
//
//            return true;
//        });
//    }
//}
package com.exampleee.agroapk;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.About_Us_Activity;
import com.exampleee.agroapk.activity.NotificationDetailsActivity;
import com.exampleee.agroapk.frags.CategoryFragment;
import com.exampleee.agroapk.frags.DiscussFragment;
import com.exampleee.agroapk.frags.HomeFragment;
import com.exampleee.agroapk.frags.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    CardView btnCart;
    ImageView companyprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCart = findViewById(R.id.btn_cart);
        companyprofile = findViewById(R.id.profile_company_logo);

        setFooter();

        btnCart.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NotificationDetailsActivity.class));
        });

        companyprofile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
        });

        // Check if we need to open DiscussFragment
        if (getIntent().getBooleanExtra("openDiscussFragment", false)) {
            openDiscussFragment();
        } else {
            // Set default fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment(MainActivity.this)).commit();
        }

        // Handle the back press using OnBackPressedDispatcher
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity(); // Close the app
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    // Method to open DiscussFragment
    private void openDiscussFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new DiscussFragment(MainActivity.this)).commit();
    }

    private void setFooter() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = new HomeFragment(MainActivity.this);

            int item_id = item.getItemId();
            if (item_id == R.id.bottom_home) {
                fragment = new HomeFragment(MainActivity.this);
            } else if (item_id == R.id.bottom_category) {
                fragment = new CategoryFragment(MainActivity.this);
            } else if (item_id == R.id.profile_action_About_US) {
                startActivity(new Intent(MainActivity.this, About_Us_Activity.class));
                return false;
            } else if (item_id == R.id.bottom_Consult) {
                fragment = new DiscussFragment(MainActivity.this);
            } else if (item_id == R.id.bottom_Profile) {
                fragment = new ProfileFragment(MainActivity.this);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();
            return true;
        });
    }

}

