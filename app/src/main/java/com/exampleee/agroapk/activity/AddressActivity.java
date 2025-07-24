package com.exampleee.agroapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.AddressAdapter;
import com.exampleee.agroapk.models.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    ProgressBar loader;
    AddressAdapter adapter;

    boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        recyclerView  = findViewById(R.id.recycler);
        loader  = findViewById(R.id.loader);

        adapter = new AddressAdapter(new ArrayList<>(), (address, position) -> {

        });


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

        loadData();

        //findViewById(R.id.tool_back).setOnClickListener(v ->finish());
        ImageView backbtn = findViewById(R.id.tool_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = findViewById(R.id.tool_title);
        title.setText(getString(R.string.my_address));


        findViewById(R.id.address_add).setOnClickListener(v -> {
            startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));

        });

    }


    private void loadData() {
        isLoading= true;

        loader.setVisibility(View.VISIBLE);

        List<Address> list = new ArrayList<>();





        list.add(new Address("0","0","Green Care AroTech","+91 92720765745","greencare@gmail.com","Shrirampur","Sai Hospital","413739","Shrirampur","Maharashtra",true));
        list.add(new Address("0","0","Green Care AroTech","+91 92720765745","greencare@gmail.com","Shrirampur","Sai Hospital","413739","Shrirampur","Maharashtra",false));



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
