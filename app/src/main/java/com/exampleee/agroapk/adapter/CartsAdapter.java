package com.exampleee.agroapk.adapter;

import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.DetailsActivity;
import com.exampleee.agroapk.models.CartAction;
import com.exampleee.agroapk.models.CartsItem;
import com.exampleee.agroapk.models.Products;

import java.util.List;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.CartsHolder> {


    public List<CartsItem> list;
    CartAction action;

    public CartsAdapter(List<CartsItem> list, CartAction action) {
        this.list = list;
        this.action = action;
    }

    @NonNull
    @Override
    public CartsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartsHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carts,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull CartsHolder holder, int position) {


        CartsItem cartsItem = list.get(position);

        holder.title.setText(cartsItem.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(cartsItem.getCover())
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(holder.cover);

        // copy an pase from PRODUCTADAPTER if erorrrrrrrrrrrrrr


        String price = "₹" + cartsItem.getPrice() ; // No change needed
        String Sprice = "<del>₹" + cartsItem.getSprice() + "</del>"; // Change this line


            //off formulla
        int off_value =(int) (100 - (((double) cartsItem.getSprice() / cartsItem.getPrice()) * 100));
        String off = off_value+ "%OFF";


        holder.price.setText(Html.fromHtml(price));
        holder.sprice.setText(Html.fromHtml(Sprice));
        holder.off.setText(off);
        holder.quantity.setText(String.valueOf(cartsItem.getQuantity()));


        holder.cover.setOnClickListener(v -> {
            Products.products_id=cartsItem.getProduct_id();
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), DetailsActivity.class));
        });

        holder.add.setOnClickListener(v -> {
            action.onCartQuantity(cartsItem,position,"add");

        });

        holder.remove.setOnClickListener(v -> {
            action.onCartQuantity(cartsItem,position,"remove");


        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }


    public void insertData(List<CartsItem> items){

        int positionStart = getItemCount();
        int item_count = items.size();
        this.list.addAll(items);
        notifyItemRangeInserted(positionStart,item_count);

    }

    public void clearAll(){

        this.list.clear();
        notifyDataSetChanged();
    }


    public static class CartsHolder extends RecyclerView.ViewHolder {

        ImageView cover,add,remove;
        TextView title,price,sprice,off,quantity;

        public CartsHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_cart_cover);
            add = itemView.findViewById(R.id.item_cart_add);
            remove = itemView.findViewById(R.id.item_cart_remove);

            title = itemView.findViewById(R.id.item_cart_title);
            price = itemView.findViewById(R.id.item_cart_price);
            sprice = itemView.findViewById(R.id.item_cart_sprice);
            off = itemView.findViewById(R.id.item_cart_off);
            quantity = itemView.findViewById(R.id.item_cart_quantity);

        }
    }
}
