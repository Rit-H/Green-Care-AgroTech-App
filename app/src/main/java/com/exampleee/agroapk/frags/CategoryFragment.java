package com.exampleee.agroapk.frags;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.CategoryAdapter;
import com.exampleee.agroapk.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    View layout;

    Activity activity;

    public CategoryFragment(Activity activity) {
        this.activity = activity;
    }



    RecyclerView recyclerView;
    List<Category> categoryList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_category,container,false);
        recyclerView= layout.findViewById(R.id.recycler_category);


        loadCategory();





        return layout;
    }

//    private void loadCategory() {
//
//        categoryList.add(new Category("0","Bio Pesticide","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8xy7rrHKrX2UxEFTXg4pg8di4wfv3KGLp8g&usqp=CAU"));
//        categoryList.add(new Category("1","Bio Viricide","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGceb3pFDU7LR9q8LilcZrZaHe2cKvoyd15A&usqp=CAU"));
//        categoryList.add(new Category("2","Broadcast","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIfXYvS7JC3gHRbb-j-sPR7QmG2iV7tYg_-w&usqp=CAU"));
//        categoryList.add(new Category("3","Honey Attractant","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIfXYvS7JC3gHRbb-j-sPR7QmG2iV7tYg_-w&usqp=CAU"));
//        categoryList.add(new Category("4","Micronutrient","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIfXYvS7JC3gHRbb-j-sPR7QmG2iV7tYg_-w&usqp=CAU"));
//        categoryList.add(new Category("5","Water Soluble Fertilizer","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS33ttsLmtdIPkn2qEDFli4wkSRnEmnUxkilg&usqp=CAU"));
//        categoryList.add(new Category("6","Plant Growth Promoter","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIfXYvS7JC3gHRbb-j-sPR7QmG2iV7tYg_-w&usqp=CAU"));
//        categoryList.add(new Category("7","Plant Growth Regulator","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_0aPorc-a2U97SHRkLj6ovvGtSJfxXzh9Hw&usqp=CAU"));
//        categoryList.add(new Category("8","Adjuvant and PH balance","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXDVq8f2V0ThlkOzmIfEdK_GRf_Jg7i0-Cyw&usqp=CAU"));
//        categoryList.add(new Category("9","Bio Fertilizer","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXDVq8f2V0ThlkOzmIfEdK_GRf_Jg7i0-Cyw&usqp=CAU"));
//        categoryList.add(new Category("10","Bio Fungicide","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXDVq8f2V0ThlkOzmIfEdK_GRf_Jg7i0-Cyw&usqp=CAU"));
//
//
//        CategoryAdapter adapter=new CategoryAdapter(categoryList,true);
//        recyclerView.setAdapter(adapter);
//
//
//
//    }

    private void loadCategory() {
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
                    CategoryAdapter adapter = new CategoryAdapter(categoryList, true);
                    recyclerView.setAdapter(adapter);
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

}