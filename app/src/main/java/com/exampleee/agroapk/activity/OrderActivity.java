package com.exampleee.agroapk.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.OrdersAdapter;
import com.exampleee.agroapk.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar loader;
    OrdersAdapter adapter;

    boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView  = findViewById(R.id.recycler);
        loader  = findViewById(R.id.loader);

        adapter = new OrdersAdapter(new ArrayList<>());


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
        title.setText(getString(R.string.my_orders));

    }


    private void loadData() {
        isLoading= true;

        loader.setVisibility(View.VISIBLE);

        List<Order> list = new ArrayList<>();


        list.add(new Order("0","Medicine 1","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 2","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 3","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 4","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 5","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 6","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 7","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 8","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 9","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));
        list.add(new Order("0","Medicine 10","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b","25 April","PENDING","499","1"));

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
