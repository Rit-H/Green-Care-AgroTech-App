package com.exampleee.agroapk.frags;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.CategoryAdapter;
import com.exampleee.agroapk.adapter.ProductAdapter;
import com.exampleee.agroapk.adapter.SliderAdapter;
import com.exampleee.agroapk.models.Category;
import com.exampleee.agroapk.models.Products;
import com.exampleee.agroapk.models.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    View layout;

    Activity activity;
    RecyclerView categoryRecycler;
    ProgressBar progressBarHome;

    public HomeFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        layout = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecycler = layout.findViewById(R.id.recycler_category); // Initialize categoryRecycler
        progressBarHome = layout.findViewById(R.id.progressBarHome);
        progressBarHome.setVisibility(View.VISIBLE);

        setSlider();
        setCategory(); // Call loadCategory to populate categoryRecycler

        return layout;
    }

    private void setSlider() {
        SliderView sliderView = layout.findViewById(R.id.imageSlider);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("slider/sliderList");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Slider> sliderList = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                        String caption = dataSnapshot.child("caption").getValue(String.class);
                        String additionalInfo = dataSnapshot.child("additionalInfo").getValue(String.class);

                        // Create a Slider object using the retrieved data
                        Slider slider = new Slider(imageUrl, caption, additionalInfo);

                        // Add the slider to your sliderList
                        sliderList.add(slider);
                    }

                    // Set up your SliderView with the populated sliderList
                    SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
                    sliderView.setSliderAdapter(sliderAdapter);
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setScrollTimeInSec(2);
                    sliderView.startAutoCycle();
                } else {
                    Log.i("FirebaseData", "No sliders found in the database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error here if needed
            }
        });
    }


    private void setCategory() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("category/categories");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Category> categoryList = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.child("id").getValue(String.class);
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);

                        // Create a Category object using the retrieved data
                        Category category = new Category(id, name, imageUrl);

                        // Add the category to your categoryList
                        categoryList.add(category);
                    }

                    // Set up your RecyclerView with the populated categoryList
                    CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList, false);
                    categoryRecycler.setAdapter(categoryAdapter);
                    setProduct();
                } else {
                    Log.i("FirebaseData", "No categories found in the database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error here if needed
            }
        });
    }


    private void setProduct() {



        RecyclerView productRecycler = layout.findViewById(R.id.recycler_product);
        List<Products> productsList = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(productsList, true); // Initialize the adapter

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

        productRecycler.setHasFixedSize(true);
        productRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // Set the adapter
        productRecycler.setAdapter(productAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String id = dataSnapshot.child("id").getValue(String.class);
                        String title = dataSnapshot.child("title").getValue(String.class);
                        String cover = dataSnapshot.child("cover").getValue(String.class);
                        String infocover = dataSnapshot.child("infocover").getValue(String.class);
                        int price = dataSnapshot.child("price").getValue(Integer.class);
                        int sprice = dataSnapshot.child("sprice").getValue(Integer.class);
                        String categoryId = dataSnapshot.child("category").getValue(String.class);
                        String description = dataSnapshot.child("description").getValue(String.class);

                        Log.i("FirebaseData", "ID: " + id);
                        Log.i("FirebaseData", "Title: " + title);
                        Log.i("FirebaseData", "Cover: " + cover);
                        Log.i("FirebaseData", "infoCover: " + infocover);
                        Log.i("FirebaseData", "Price: " + price);
                        Log.i("FirebaseData", "SPrice: " + sprice);
                        Log.i("FirebaseData", "Description: " + description);

                        // Create a new Products object using the retrieved data
                        Products product = new Products(id, title, cover, price, sprice, infocover, categoryId, description);

                        // Add the product to your productsList
                        productsList.add(product);
                    }
                    // Notify the adapter that the data set has changed
                    productAdapter.notifyDataSetChanged();

                    // Log a message indicating that data retrieval was successful
                    Log.i("FirebaseData", "Data retrieved successfully");
                } else {
                    // Log a message indicating that the data snapshot does not exist
                    Log.i("FirebaseData", "Data snapshot does not exist");
                }
                progressBarHome.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error here if needed
            }
        });
    }

}
