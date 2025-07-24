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
import com.exampleee.agroapk.adapter.CartsAdapter;
import com.exampleee.agroapk.models.CartAction;
import com.exampleee.agroapk.models.CartsItem;

import java.util.ArrayList;
import java.util.List;

public class CartsActivety  extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar loader;
    CartsAdapter adapter;

    boolean isLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);

        recyclerView  = findViewById(R.id.recycler);
        loader  = findViewById(R.id.loader);

            adapter = new CartsAdapter(new ArrayList<>(), new CartAction() {


                //click on plus or minus
            @Override
            public void onCartQuantity(CartsItem cartsItem, int pos, String type) {


                if (type.equals("add")) {
                    cartsItem.setQuantity(cartsItem.getQuantity() + 1);
                    adapter.list.set(pos,cartsItem);
                    adapter.notifyItemChanged(pos);

                }else {
                    if (cartsItem.getQuantity()>0){
                        cartsItem.setQuantity(cartsItem.getQuantity() - 1);
                        adapter.list.set(pos,cartsItem);
                        adapter.notifyItemChanged(pos);
                    }else {
                        adapter.list.remove(pos);
                        adapter.notifyItemRemoved(pos);
                        adapter.notifyItemRangeChanged(pos,adapter.list.size());
                    }
                }
            }
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
        title.setText(getString(R.string.my_carts));

    }


    private void loadData() {
        isLoading= true;

        loader.setVisibility(View.VISIBLE);

        List<CartsItem> list = new ArrayList<>();

        list.add(new CartsItem("0","1","Medicine 1","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",5,50,40));
        list.add(new CartsItem("0","1","Medicine 2","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",4,500,300));
        list.add(new CartsItem("0","1","Medicine 3","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",15,700,400));
        list.add(new CartsItem("0","1","Medicine 4","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",1,900,800));
        list.add(new CartsItem("0","1","Medicine 5","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",7,800,500));
        list.add(new CartsItem("0","1","Medicine 6","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",9,4000,4320));
        list.add(new CartsItem("0","1","Medicine 7","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",31,100,80));
        list.add(new CartsItem("0","1","Medicine 8","https://img.freepik.com/free-vector/pharmacy-paper-bag-medicine_603843-3825.jpg?w=740&t=st=1710397242~exp=1710397842~hmac=db10360c0c8868cfa0e84ba3bd50e72d2ff80c1856b9e5ae3a65875d0f6dcd9b",5,1000,400));


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