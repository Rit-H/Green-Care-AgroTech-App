////package com.example.agroapk.activity;
////
////import static android.app.PendingIntent.getActivity;
////
////import androidx.annotation.NonNull;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.util.Log;
////import android.view.View;
////import android.widget.ImageView;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import com.bumptech.glide.Glide;
////import com.example.agroapk.MainActivity;
////import com.example.agroapk.R;
////import com.example.agroapk.adapter.ProductAdapter;
////import com.example.agroapk.helper.Helper;
////import com.example.agroapk.models.Products;
////import com.example.agroapk.models.Slider;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import com.example.agroapk.adapter.SliderAdapter;
////import com.google.firebase.database.DataSnapshot;
////import com.google.firebase.database.DatabaseError;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////import com.google.firebase.database.Query;
////import com.google.firebase.database.ValueEventListener;
////import com.smarteist.autoimageslider.SliderView;
////
////public class DetailsActivity extends AppCompatActivity {
////    String product_id = Products.products_id;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_details);
////
////        Intent intent = getIntent();
////        if (intent != null) {
////            String productId = intent.getStringExtra("product_id");
////            String productName = intent.getStringExtra("product_name");
////            String productImageUrl = intent.getStringExtra("product_image_url");
////            String productInfo=intent.getStringExtra("product_info_url");
////            int productPrice = intent.getIntExtra("product_price", 0);
////            int productDiscount = intent.getIntExtra("product_discount", 0);
////            String categoryId = intent.getStringExtra("categoryId");
////
////            // Print retrieved data to log
////            String TAG = "DetailsActivity";
////            Log.i(TAG, "Product ID: " + productId);
////            Log.i(TAG, "Product Name: " + productName);
////            Log.i(TAG, "Product Image URL: " + productImageUrl);
////            Log.i(TAG, "Product Image URL: " + productInfo);
////            Log.i(TAG, "Product Price: " + productPrice);
////            Log.i(TAG, "Product Discount: " + productDiscount);
////
////            TextView titleTextView = findViewById(R.id.product_title);
////            titleTextView.setText(productName);
////
////            TextView priceTextView = findViewById(R.id.product_price);
////            priceTextView.setText(String.valueOf(productPrice));
////
//////            TextView buynow=findViewById(R.id.buynow);
//////            buynow.setText(String.valueOf(productPrice));
////
////            TextView sPriceTextView = findViewById(R.id.product_sprice);
////            sPriceTextView.setText(String.valueOf(productDiscount));
////            setSlider(productImageUrl,productInfo);
////            loadRelated(productId,productName,productImageUrl,productPrice,productDiscount,productInfo, categoryId);
////
////            // Load image using Glide or any other library to your ImageView
//////            ImageView imageView = findViewById(R.id.thumbnailSlider);
//////            Glide.with(this).load(productImageUrl).into(imageView);
////
////
////        } else {
////            Toast.makeText(this, "No product details found", Toast.LENGTH_SHORT).show();
////            goBack(true);
////        }
////
////        // Handle crashing app
//////        if (product_id == null || product_id.length() == 0) {
//////            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
//////            goBack(true);
//////        }
////
////
////
////        loadData();
////    }
////
////    private void loadData() {
//////        loadRelated();
////
////    }
////
////    private void loadRelated(final String id, final String name, final String url, final int price, final int sprice, final String infourl, final String categoryId) {
////        final RecyclerView productRecycler = findViewById(R.id.recycler_related);
////        final List<Products> productsList = new ArrayList<>();
////
////        // Add the current product to the list
////        productsList.add(new Products(id, name, url, price, sprice, infourl, categoryId));
////
////        // Get a reference to the "products" node in the database
////        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");
////
////        // Query the database to get the next product after the current product
////        productsRef.orderByKey().startAt(id).limitToFirst(2).addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                // Skip the first product as it's the current product
////                boolean firstProductSkipped = false;
////
////                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    if (!firstProductSkipped) {
////                        firstProductSkipped = true;
////                        continue;
////                    }
////
////                    // Get data of the next product
////                    String nextProductId = snapshot.child("id").getValue(String.class);
////                    String nextProductName = snapshot.child("title").getValue(String.class);
////                    String nextProductImageUrl = snapshot.child("cover").getValue(String.class);
////                    int nextProductPrice = snapshot.child("price").getValue(Integer.class);
////                    int nextProductSPrice = snapshot.child("sprice").getValue(Integer.class);
////                    String nextProductInfoUrl = snapshot.child("infocover").getValue(String.class);
////                    String nextCategoryId = snapshot.child("category").getValue(String.class);
////
////                    String TAG="greencareagrotech";
////
////                    Log.i(TAG, "Next Product ID: " + nextProductId);
////                    Log.i(TAG, "Next Product Name: " + nextProductName);
////                    Log.i(TAG, "Next Product Image URL: " + nextProductImageUrl);
////                    Log.i(TAG, "Next Product Price: " + nextProductPrice);
////                    Log.i(TAG, "Next Product SPrice: " + nextProductSPrice);
////                    Log.i(TAG, "Next Product Info URL: " + nextProductInfoUrl);
////
////                    // Add the next product to the list
////                    productsList.add(new Products(nextProductId, nextProductName, nextProductImageUrl, nextProductPrice, nextProductSPrice, nextProductInfoUrl, nextCategoryId));
////
////                    // Create and set the adapter
////                    ProductAdapter productAdapter = new ProductAdapter(productsList, false);
////                    productRecycler.setAdapter(productAdapter);
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError databaseError) {
////                Log.e("Error", "Failed to load related products: " + databaseError.getMessage());
////                // Handle error
////            }
////        });
////    }
////
////
////
//////    private void loadRelated(String id,String name,String url,int price,int sprice,String infourl) {
//////        RecyclerView productRecycler = findViewById(R.id.recycler_related);
//////        List<Products> productsList = new ArrayList<>();
//////
//////        // Add your product data here
//////        productsList.add(new Products(id,name,url,price,sprice,infourl));
//////        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150,""));
//////        // Add more products as needed
//////
//////        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//////        productRecycler.setAdapter(productAdapter);
//////    }
////
////    private void setSlider(String productImageUrl,String infourl) {
////        SliderView sliderView = findViewById(R.id.thumbnailSlider);
////        List<Slider> sliderList = new ArrayList<>();
////
////        // Add product image URL to slider list
////        sliderList.add(new Slider(productImageUrl, "", ""));
////        sliderList.add(new Slider(infourl, "", ""));
////
////        // Clear existing slider images
//////        sliderList.clear();
////
////        // Add your slider images here
////       // sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/hive_bulk_sender.jpeg", "", ""));
//////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/ctet_quiz_app.jpeg", "", ""));
//////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/news_11.jpeg", "", ""));
//////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/love_status.jpeg", "", ""));
//////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/fff_short_video.jpeg", "", ""));
////
////        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
////        sliderView.setSliderAdapter(sliderAdapter);
////        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
////        sliderView.setScrollTimeInSec(2);
////        sliderView.startAutoCycle();
////    }
////
////
////
////    @Override
////    public void onBackPressed() {
////        super.onBackPressed();
////        goBack(false);
////    }
////
////    public void onAddTOCartClicked (View view) {
////        // Handle the buy action here
////        Toast.makeText(this, "Item Added successfully!", Toast.LENGTH_SHORT).show();
////        // You can add more logic here, like navigating to a checkout activity, etc.
////    }
////
////    public void onBuyNowClicked(View view) {
////        // Handle the buy action here
////        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
////        // You can add more logic here, like navigating to a checkout activity, etc.
////    }
////
////    private void goBack(boolean fromIntent) {
////        if (Helper.fromNotification && fromIntent) {
////            Helper.fromNotification = false;
////            startActivity(new Intent(DetailsActivity.this, MainActivity.class));
////        } else {
////            if (fromIntent) {
////                finish();
////            }
////            super.onBackPressed();
////        }
////    }
////}
////
////
////
/////*      RecyclerView productRecycler = findViewById(R.id.recycler_related);
////        List<Products> productsList = new ArrayList<>();
////
////        // Add your product data here
////        productsList.add(new Products("0", "Medicine 1", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 100, 50));
////        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150));
////        // Add more products as needed
////
////        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
////        productRecycler.setAdapter(productAdapter);
////    }*/
//
//package com.example.agroapk.activity;
//
//import static android.app.PendingIntent.getActivity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.example.agroapk.MainActivity;
//import com.example.agroapk.R;
//import com.example.agroapk.adapter.ProductAdapter;
//import com.example.agroapk.frags.DiscussFragment;
//import com.example.agroapk.helper.Helper;
//import com.example.agroapk.models.Products;
//import com.example.agroapk.models.Slider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.agroapk.adapter.SliderAdapter;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.smarteist.autoimageslider.SliderView;
//
//public class DetailsActivity extends AppCompatActivity {
//    String product_id = Products.products_id;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String productId = intent.getStringExtra("product_id");
//            String productName = intent.getStringExtra("product_name");
//            String productImageUrl = intent.getStringExtra("product_image_url");
//            String productInfo=intent.getStringExtra("product_info_url");
//            int productPrice = intent.getIntExtra("product_price", 0);
//            int productDiscount = intent.getIntExtra("product_discount", 0);
//            String categoryId = intent.getStringExtra("categoryId");
//
//            // Print retrieved data to log
//            String TAG = "DetailsActivity";
//            Log.i(TAG, "Product ID: " + productId);
//            Log.i(TAG, "Product Name: " + productName);
//            Log.i(TAG, "Product Image URL: " + productImageUrl);
//            Log.i(TAG, "Product Image URL: " + productInfo);
//            Log.i(TAG, "Product Price: " + productPrice);
//            Log.i(TAG, "Product Discount: " + productDiscount);
//
//            TextView titleTextView = findViewById(R.id.product_title);
//            titleTextView.setText(productName);
//
////            TextView priceTextView = findViewById(R.id.product_price);
////            priceTextView.setText(String.valueOf(productPrice));
//
////            TextView buynow=findViewById(R.id.buynow);
////            buynow.setText(String.valueOf(productPrice));
//
////            TextView sPriceTextView = findViewById(R.id.product_sprice);
////            sPriceTextView.setText(String.valueOf(productDiscount));
//            setSlider(productImageUrl,productInfo);
//            loadRelated(productId,productName,productImageUrl,productPrice,productDiscount,productInfo, categoryId);
//
//            // Load image using Glide or any other library to your ImageView
////            ImageView imageView = findViewById(R.id.thumbnailSlider);
////            Glide.with(this).load(productImageUrl).into(imageView);
//
//
//        } else {
//            Toast.makeText(this, "No product details found", Toast.LENGTH_SHORT).show();
//            goBack(true);
//        }
//
//        // Handle crashing app
////        if (product_id == null || product_id.length() == 0) {
////            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
////            goBack(true);
////        }
//
//
//
//        loadData();
//    }
//
//    private void loadData() {
////        loadRelated();
//
//    }
//
//    private void loadRelated(final String id, final String name, final String url, final int price, final int sprice, final String infourl, final String categoryId) {
//        final RecyclerView productRecycler = findViewById(R.id.recycler_related);
//        final List<Products> productsList = new ArrayList<>();
//
//        // Add the current product to the list
//        productsList.add(new Products(id, name, url, price, sprice, infourl, categoryId));
//
//        // Get a reference to the "products" node in the database
//        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");
//
//        // Query the database to get the next product after the current product
//        productsRef.orderByKey().startAt(id).limitToFirst(2).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Skip the first product as it's the current product
//                boolean firstProductSkipped = false;
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (!firstProductSkipped) {
//                        firstProductSkipped = true;
//                        continue;
//                    }
//
//                    // Get data of the next product
//                    String nextProductId = snapshot.child("id").getValue(String.class);
//                    String nextProductName = snapshot.child("title").getValue(String.class);
//                    String nextProductImageUrl = snapshot.child("cover").getValue(String.class);
//                    int nextProductPrice = snapshot.child("price").getValue(Integer.class);
//                    int nextProductSPrice = snapshot.child("sprice").getValue(Integer.class);
//                    String nextProductInfoUrl = snapshot.child("infocover").getValue(String.class);
//                    String nextCategoryId = snapshot.child("category").getValue(String.class);
//
//                    String TAG="greencareagrotech";
//
//                    Log.i(TAG, "Next Product ID: " + nextProductId);
//                    Log.i(TAG, "Next Product Name: " + nextProductName);
//                    Log.i(TAG, "Next Product Image URL: " + nextProductImageUrl);
//                    Log.i(TAG, "Next Product Price: " + nextProductPrice);
//                    Log.i(TAG, "Next Product SPrice: " + nextProductSPrice);
//                    Log.i(TAG, "Next Product Info URL: " + nextProductInfoUrl);
//
//                    // Add the next product to the list
//                    productsList.add(new Products(nextProductId, nextProductName, nextProductImageUrl, nextProductPrice, nextProductSPrice, nextProductInfoUrl, nextCategoryId));
//
//                    // Create and set the adapter
//                    ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//                    productRecycler.setAdapter(productAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("Error", "Failed to load related products: " + databaseError.getMessage());
//                // Handle error
//            }
//        });
//    }
//
//
//
////    private void loadRelated(String id,String name,String url,int price,int sprice,String infourl) {
////        RecyclerView productRecycler = findViewById(R.id.recycler_related);
////        List<Products> productsList = new ArrayList<>();
////
////        // Add your product data here
////        productsList.add(new Products(id,name,url,price,sprice,infourl));
////        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150,""));
////        // Add more products as needed
////
////        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
////        productRecycler.setAdapter(productAdapter);
////    }
//
//    private void setSlider(String productImageUrl,String infourl) {
//        SliderView sliderView = findViewById(R.id.thumbnailSlider);
//        List<Slider> sliderList = new ArrayList<>();
//
//        // Add product image URL to slider list
//        sliderList.add(new Slider(productImageUrl, "", ""));
//        sliderList.add(new Slider(infourl, "", ""));
//
//        // Clear existing slider images
////        sliderList.clear();
//
//        // Add your slider images here
//        // sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/hive_bulk_sender.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/ctet_quiz_app.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/news_11.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/love_status.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/fff_short_video.jpeg", "", ""));
//
//        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        sliderView.setScrollTimeInSec(2);
//        sliderView.startAutoCycle();
//    }
//
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        goBack(false);
//    }
//
//    public void onAddTOCartClicked (View view) {
//        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
//        // Handle the buy action here
//
//        Toast.makeText(this, "Item Added successfully!", Toast.LENGTH_SHORT).show();
//        // You can add more logic here, like navigating to a checkout activity, etc.
//    }
//
//
//    public void onBuyNowClicked(View view) {
//        // Handle the buy action here
//        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
//        // You can add more logic here, like navigating to a checkout activity, etc.
//    }
//
//    private void goBack(boolean fromIntent) {
//        if (Helper.fromNotification && fromIntent) {
//            Helper.fromNotification = false;
//            startActivity(new Intent(DetailsActivity.this, MainActivity.class));
//        } else {
//            if (fromIntent) {
//                finish();
//            }
//            super.onBackPressed();
//        }
//    }
//}
//
//
//
///*      RecyclerView productRecycler = findViewById(R.id.recycler_related);
//        List<Products> productsList = new ArrayList<>();
//
//        // Add your product data here
//        productsList.add(new Products("0", "Medicine 1", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 100, 50));
//        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150));
//        // Add more products as needed
//
//        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//        productRecycler.setAdapter(productAdapter);
//    }*/

//package com.example.agroapk.activity;
//
//import static android.app.PendingIntent.getActivity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.example.agroapk.MainActivity;
//import com.example.agroapk.R;
//import com.example.agroapk.adapter.ProductAdapter;
//import com.example.agroapk.helper.Helper;
//import com.example.agroapk.models.Products;
//import com.example.agroapk.models.Slider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.example.agroapk.adapter.SliderAdapter;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.smarteist.autoimageslider.SliderView;
//
//public class DetailsActivity extends AppCompatActivity {
//    String product_id = Products.products_id;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            String productId = intent.getStringExtra("product_id");
//            String productName = intent.getStringExtra("product_name");
//            String productImageUrl = intent.getStringExtra("product_image_url");
//            String productInfo=intent.getStringExtra("product_info_url");
//            int productPrice = intent.getIntExtra("product_price", 0);
//            int productDiscount = intent.getIntExtra("product_discount", 0);
//            String categoryId = intent.getStringExtra("categoryId");
//
//            // Print retrieved data to log
//            String TAG = "DetailsActivity";
//            Log.i(TAG, "Product ID: " + productId);
//            Log.i(TAG, "Product Name: " + productName);
//            Log.i(TAG, "Product Image URL: " + productImageUrl);
//            Log.i(TAG, "Product Image URL: " + productInfo);
//            Log.i(TAG, "Product Price: " + productPrice);
//            Log.i(TAG, "Product Discount: " + productDiscount);
//
//            TextView titleTextView = findViewById(R.id.product_title);
//            titleTextView.setText(productName);
//
//            TextView priceTextView = findViewById(R.id.product_price);
//            priceTextView.setText(String.valueOf(productPrice));
//
////            TextView buynow=findViewById(R.id.buynow);
////            buynow.setText(String.valueOf(productPrice));
//
//            TextView sPriceTextView = findViewById(R.id.product_sprice);
//            sPriceTextView.setText(String.valueOf(productDiscount));
//            setSlider(productImageUrl,productInfo);
//            loadRelated(productId,productName,productImageUrl,productPrice,productDiscount,productInfo, categoryId);
//
//            // Load image using Glide or any other library to your ImageView
////            ImageView imageView = findViewById(R.id.thumbnailSlider);
////            Glide.with(this).load(productImageUrl).into(imageView);
//
//
//        } else {
//            Toast.makeText(this, "No product details found", Toast.LENGTH_SHORT).show();
//            goBack(true);
//        }
//
//        // Handle crashing app
////        if (product_id == null || product_id.length() == 0) {
////            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
////            goBack(true);
////        }
//
//
//
//        loadData();
//    }
//
//    private void loadData() {
////        loadRelated();
//
//    }
//
//    private void loadRelated(final String id, final String name, final String url, final int price, final int sprice, final String infourl, final String categoryId) {
//        final RecyclerView productRecycler = findViewById(R.id.recycler_related);
//        final List<Products> productsList = new ArrayList<>();
//
//        // Add the current product to the list
//        productsList.add(new Products(id, name, url, price, sprice, infourl, categoryId));
//
//        // Get a reference to the "products" node in the database
//        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");
//
//        // Query the database to get the next product after the current product
//        productsRef.orderByKey().startAt(id).limitToFirst(2).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Skip the first product as it's the current product
//                boolean firstProductSkipped = false;
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    if (!firstProductSkipped) {
//                        firstProductSkipped = true;
//                        continue;
//                    }
//
//                    // Get data of the next product
//                    String nextProductId = snapshot.child("id").getValue(String.class);
//                    String nextProductName = snapshot.child("title").getValue(String.class);
//                    String nextProductImageUrl = snapshot.child("cover").getValue(String.class);
//                    int nextProductPrice = snapshot.child("price").getValue(Integer.class);
//                    int nextProductSPrice = snapshot.child("sprice").getValue(Integer.class);
//                    String nextProductInfoUrl = snapshot.child("infocover").getValue(String.class);
//                    String nextCategoryId = snapshot.child("category").getValue(String.class);
//
//                    String TAG="greencareagrotech";
//
//                    Log.i(TAG, "Next Product ID: " + nextProductId);
//                    Log.i(TAG, "Next Product Name: " + nextProductName);
//                    Log.i(TAG, "Next Product Image URL: " + nextProductImageUrl);
//                    Log.i(TAG, "Next Product Price: " + nextProductPrice);
//                    Log.i(TAG, "Next Product SPrice: " + nextProductSPrice);
//                    Log.i(TAG, "Next Product Info URL: " + nextProductInfoUrl);
//
//                    // Add the next product to the list
//                    productsList.add(new Products(nextProductId, nextProductName, nextProductImageUrl, nextProductPrice, nextProductSPrice, nextProductInfoUrl, nextCategoryId));
//
//                    // Create and set the adapter
//                    ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//                    productRecycler.setAdapter(productAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("Error", "Failed to load related products: " + databaseError.getMessage());
//                // Handle error
//            }
//        });
//    }
//
//
//
////    private void loadRelated(String id,String name,String url,int price,int sprice,String infourl) {
////        RecyclerView productRecycler = findViewById(R.id.recycler_related);
////        List<Products> productsList = new ArrayList<>();
////
////        // Add your product data here
////        productsList.add(new Products(id,name,url,price,sprice,infourl));
////        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150,""));
////        // Add more products as needed
////
////        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
////        productRecycler.setAdapter(productAdapter);
////    }
//
//    private void setSlider(String productImageUrl,String infourl) {
//        SliderView sliderView = findViewById(R.id.thumbnailSlider);
//        List<Slider> sliderList = new ArrayList<>();
//
//        // Add product image URL to slider list
//        sliderList.add(new Slider(productImageUrl, "", ""));
//        sliderList.add(new Slider(infourl, "", ""));
//
//        // Clear existing slider images
////        sliderList.clear();
//
//        // Add your slider images here
//       // sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/hive_bulk_sender.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/ctet_quiz_app.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/news_11.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/love_status.jpeg", "", ""));
////        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/fff_short_video.jpeg", "", ""));
//
//        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        sliderView.setScrollTimeInSec(2);
//        sliderView.startAutoCycle();
//    }
//
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        goBack(false);
//    }
//
//    public void onAddTOCartClicked (View view) {
//        // Handle the buy action here
//        Toast.makeText(this, "Item Added successfully!", Toast.LENGTH_SHORT).show();
//        // You can add more logic here, like navigating to a checkout activity, etc.
//    }
//
//    public void onBuyNowClicked(View view) {
//        // Handle the buy action here
//        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
//        // You can add more logic here, like navigating to a checkout activity, etc.
//    }
//
//    private void goBack(boolean fromIntent) {
//        if (Helper.fromNotification && fromIntent) {
//            Helper.fromNotification = false;
//            startActivity(new Intent(DetailsActivity.this, MainActivity.class));
//        } else {
//            if (fromIntent) {
//                finish();
//            }
//            super.onBackPressed();
//        }
//    }
//}
//
//
//
///*      RecyclerView productRecycler = findViewById(R.id.recycler_related);
//        List<Products> productsList = new ArrayList<>();
//
//        // Add your product data here
//        productsList.add(new Products("0", "Medicine 1", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 100, 50));
//        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150));
//        // Add more products as needed
//
//        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//        productRecycler.setAdapter(productAdapter);
//    }*/

package com.exampleee.agroapk.activity;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exampleee.agroapk.MainActivity;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.ProductAdapter;
import com.exampleee.agroapk.helper.Helper;
import com.exampleee.agroapk.models.Products;
import com.exampleee.agroapk.models.Slider;

import java.util.ArrayList;
import java.util.List;

import com.exampleee.agroapk.adapter.SliderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

public class DetailsActivity extends AppCompatActivity {
    String product_id = Products.products_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        if (intent != null) {
            String productId = intent.getStringExtra("product_id");
            String productName = intent.getStringExtra("product_name");
            String productImageUrl = intent.getStringExtra("product_image_url");
            String productInfo=intent.getStringExtra("product_info_url");
            int productPrice = intent.getIntExtra("product_price", 0);
            int productDiscount = intent.getIntExtra("product_discount", 0);
            String categoryId = intent.getStringExtra("categoryId");
            String description = intent.getStringExtra("description");

            // Print retrieved data to log
            String TAG = "DetailsActivity";
            Log.i(TAG, "Product ID: " + productId);
            Log.i(TAG, "Product Name: " + productName);
            Log.i(TAG, "Product Image URL: " + productImageUrl);
            Log.i(TAG, "Product Image URL: " + productInfo);
            Log.i(TAG, "Product Price: " + productPrice);
            Log.i(TAG, "Product Discount: " + productDiscount);
            Log.i(TAG, "Product Description: " + description);


            TextView titleTextView = findViewById(R.id.product_title);
            titleTextView.setText(productName);
            TextView descriptionTextView = findViewById(R.id.product_description);
            descriptionTextView.setText(description);

//            TextView priceTextView = findViewById(R.id.product_price);
//            priceTextView.setText(String.valueOf(productPrice));

//            TextView buynow=findViewById(R.id.buynow);
//            buynow.setText(String.valueOf(productPrice));

//            TextView sPriceTextView = findViewById(R.id.product_sprice);
//            sPriceTextView.setText(String.valueOf(productDiscount));
            setSlider(productImageUrl,productInfo);
            loadRelated(productId,productName,productImageUrl,productPrice,productDiscount,productInfo, categoryId, description);

            // Load image using Glide or any other library to your ImageView
//            ImageView imageView = findViewById(R.id.thumbnailSlider);
//            Glide.with(this).load(productImageUrl).into(imageView);


        } else {
            Toast.makeText(this, "No product details found", Toast.LENGTH_SHORT).show();
            goBack(true);
        }

        // Handle crashing app
//        if (product_id == null || product_id.length() == 0) {
//            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
//            goBack(true);
//        }



        loadData();
    }

    private void loadData() {
//        loadRelated();

    }

    private void loadRelated(final String id, final String name, final String url, final int price, final int sprice, final String infourl, final String categoryId, final String description) {
        final RecyclerView productRecycler = findViewById(R.id.recycler_related);
        final List<Products> productsList = new ArrayList<>();

        // Add the current product to the list
        productsList.add(new Products(id, name, url, price, sprice, infourl, categoryId, description));

        // Get a reference to the "products" node in the database
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("products");

        // Query the database to get the next product after the current product
        productsRef.orderByKey().startAt(id).limitToFirst(2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Skip the first product as it's the current product
                boolean firstProductSkipped = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (!firstProductSkipped) {
                        firstProductSkipped = true;
                        continue;
                    }

                    // Get data of the next product
                    String nextProductId = snapshot.child("id").getValue(String.class);
                    String nextProductName = snapshot.child("title").getValue(String.class);
                    String nextProductImageUrl = snapshot.child("cover").getValue(String.class);
                    int nextProductPrice = snapshot.child("price").getValue(Integer.class);
                    int nextProductSPrice = snapshot.child("sprice").getValue(Integer.class);
                    String nextProductInfoUrl = snapshot.child("infocover").getValue(String.class);
                    String nextCategoryId = snapshot.child("category").getValue(String.class);
                    String nextProductDescription = snapshot.child("description").getValue(String.class);

                    String TAG="greencareagrotech";

                    Log.i(TAG, "Next Product ID: " + nextProductId);
                    Log.i(TAG, "Next Product Name: " + nextProductName);
                    Log.i(TAG, "Next Product Image URL: " + nextProductImageUrl);
                    Log.i(TAG, "Next Product Price: " + nextProductPrice);
                    Log.i(TAG, "Next Product SPrice: " + nextProductSPrice);
                    Log.i(TAG, "Next Product Info URL: " + nextProductInfoUrl);
                    Log.i(TAG, "Next Product Info URL: " + nextProductDescription);

                    // Add the next product to the list
                    productsList.add(new Products(nextProductId, nextProductName, nextProductImageUrl, nextProductPrice, nextProductSPrice, nextProductInfoUrl, nextCategoryId, nextProductDescription));

                    // Create and set the adapter
                    ProductAdapter productAdapter = new ProductAdapter(productsList, false);
                    productRecycler.setAdapter(productAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", "Failed to load related products: " + databaseError.getMessage());
                // Handle error
            }
        });
    }



//    private void loadRelated(String id,String name,String url,int price,int sprice,String infourl) {
//        RecyclerView productRecycler = findViewById(R.id.recycler_related);
//        List<Products> productsList = new ArrayList<>();
//
//        // Add your product data here
//        productsList.add(new Products(id,name,url,price,sprice,infourl));
//        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150,""));
//        // Add more products as needed
//
//        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
//        productRecycler.setAdapter(productAdapter);
//    }

    private void setSlider(String productImageUrl,String infourl) {
        SliderView sliderView = findViewById(R.id.thumbnailSlider);
        List<Slider> sliderList = new ArrayList<>();

        // Add product image URL to slider list
        sliderList.add(new Slider(productImageUrl, "", ""));
        sliderList.add(new Slider(infourl, "", ""));

        // Clear existing slider images
//        sliderList.clear();

        // Add your slider images here
        // sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/hive_bulk_sender.jpeg", "", ""));
//        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/ctet_quiz_app.jpeg", "", ""));
//        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/news_11.jpeg", "", ""));
//        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/love_status.jpeg", "", ""));
//        sliderList.add(new Slider("https://www.chikuaicode.com/assets/recent/fff_short_video.jpeg", "", ""));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(2);
        sliderView.startAutoCycle();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack(false);
    }

    public void onAddTOCartClicked (View view) {
       // startActivity(new Intent(DetailsActivity.this, MainActivity.class));
        // Handle the buy action here

        Toast.makeText(this, "Go to Consult Section for Call", Toast.LENGTH_SHORT).show();
        // You can add more logic here, like navigating to a checkout activity, etc.
    }

    public void onBuyNowClicked(View view) {
        // Navigate to MainActivity with a flag to open DiscussFragment
        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
        intent.putExtra("openDiscussFragment", true);
        startActivity(intent);
    }


    private void goBack(boolean fromIntent) {
        if (Helper.fromNotification && fromIntent) {
            Helper.fromNotification = false;
            startActivity(new Intent(DetailsActivity.this, MainActivity.class));
        } else {
            if (fromIntent) {
                finish();
            }
            super.onBackPressed();
        }
    }
}



/*      RecyclerView productRecycler = findViewById(R.id.recycler_related);
        List<Products> productsList = new ArrayList<>();

        // Add your product data here
        productsList.add(new Products("0", "Medicine 1", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 100, 50));
        productsList.add(new Products("1", "Medicine 2", "https://images.pexels.com/photos/105028/pexels-photo-105028.jpeg?auto=compress&cs=tinysrgb&w=600", 200, 150));
        // Add more products as needed

        ProductAdapter productAdapter = new ProductAdapter(productsList, false);
        productRecycler.setAdapter(productAdapter);
    }*/