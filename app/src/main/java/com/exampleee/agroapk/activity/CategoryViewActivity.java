package com.exampleee.agroapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.ProductAdapter;
import com.exampleee.agroapk.models.Category;
import com.exampleee.agroapk.models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewActivity extends AppCompatActivity {

    Category category = Category.category;

    RecyclerView recyclerView;
    ProgressBar loader;
    ProductAdapter adapter;
    String categoryId;

    boolean isLoading;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        intent = getIntent();
        categoryId = intent.getStringExtra("categoryId");
        recyclerView  = findViewById(R.id.recycler);
        loader  = findViewById(R.id.loader);

        adapter = new ProductAdapter(new ArrayList<>(),true);

        recyclerView.setAdapter(adapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isLoading){
                    return;
                }

                if (!recyclerView.canScrollVertically(1) && dy > 0 ){

                    loadData();
                }

            }
        });

//        loadData();
        setProduct();

        //findViewById(R.id.tool_back).setOnClickListener(v ->finish());
        ImageView backbtn = findViewById(R.id.tool_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = findViewById(R.id.tool_title);

        title.setText(category.getTitle());




    }

    private void setProduct() {



        RecyclerView productRecycler = recyclerView;
        List<Products> productsList = new ArrayList<>();
        ProductAdapter productAdapter = new ProductAdapter(productsList, true); // Initialize the adapter

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

        productRecycler.setHasFixedSize(true);
        productRecycler.setLayoutManager(new GridLayoutManager(CategoryViewActivity.this, 2));


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
                        String categoryIdDb = dataSnapshot.child("category").getValue(String.class);
                        String description = dataSnapshot.child("description").getValue(String.class);

                        if (categoryIdDb.equals(categoryId)) {

                            Log.i("FirebaseData", "ID: " + id);
                            Log.i("FirebaseData", "Title: " + title);
                            Log.i("FirebaseData", "Cover: " + cover);
                            Log.i("FirebaseData", "infoCover: " + infocover);
                            Log.i("FirebaseData", "Price: " + price);
                            Log.i("FirebaseData", "SPrice: " + sprice);

                            // Create a new Products object using the retrieved data
                            Products product = new Products(id, title, cover, price, sprice, infocover, categoryId, description);

                            // Add the product to your productsList
                            productsList.add(product);
                        }


                    }
                    // Notify the adapter that the data set has changed
                    productAdapter.notifyDataSetChanged();

                    // Log a message indicating that data retrieval was successful
                    Log.i("FirebaseData", "Data retrieved successfully");
                } else {
                    // Log a message indicating that the data snapshot does not exist
                    Log.i("FirebaseData", "Data snapshot does not exist");
                }
                loader.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error here if needed
            }
        });
    }

    private void loadData() {
        isLoading= true;

        loader.setVisibility(View.VISIBLE);

        List<Products> list = new ArrayList<>();





//        list.add(new Products("0","Bullet","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",225,230,""));
//        list.add(new Products("1","Booster Gold","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",200,150,""));
//        list.add(new Products("2","Medicine 3","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",500,365,""));
//        list.add(new Products("3","Medicine 4","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",640,562,""));
//        list.add(new Products("4","Medicine 5","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",465,362,""));
//        list.add(new Products("5","Medicine 6","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",365,268,""));
//        list.add(new Products("6","Medicine 7","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",100,50,""));
//        list.add(new Products("7","Medicine 8","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",200,150,""));
//        list.add(new Products("8","Medicine 9","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",500,365,""));
//        list.add(new Products("9","Medicine 10","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",640,562,""));
//        list.add(new Products("10","Medicine 11","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",465,362,""));
//        list.add(new Products("11","Medicine 12","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",365,268,""));

        //lise

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter.insertData(list);
                loader.setVisibility(View.GONE);
                isLoading=false;

            }
        },2000);

    }
}