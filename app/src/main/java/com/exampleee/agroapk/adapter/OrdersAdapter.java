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
import com.exampleee.agroapk.activity.OrderDetailsActivity;
import com.exampleee.agroapk.models.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderHolder>{

    List<Order> list;

    public OrdersAdapter(List<Order> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OrdersAdapter.OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.OrderHolder holder, int position) {

        Order order=list.get(position);

        holder.title.setText(order.getTitle());

        holder.status.setText(order.getDelivery_status());


        Glide.with(holder.itemView.getContext())
                .load(order.getCover())
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into(holder.cover);

        // copy an paste from PRODUCTADAPTER if erorrrrrrrrrrrrrr

        String price = "₹" + order.getPrice() ; // No change needed

        holder.price.setText(Html.fromHtml(price));

        /*holder.cover.setOnClickListener(v -> {
            Products.products_id=order.getProduct_id();
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), DetailsActivity.class));
        });*/

        holder.itemView.setOnClickListener(v -> {

            Order.order=order;
            holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), OrderDetailsActivity.class));

        });






    }

    public void insertData(List<Order> items){

        int positionStart = getItemCount();
        int item_count = items.size();
        this.list.addAll(items);
        notifyItemRangeInserted(positionStart,item_count);

    }

    public void clearAll(){

        this.list.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrderHolder extends RecyclerView.ViewHolder{


        ImageView cover;
        TextView title,price,status;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.item_order_cover);
            title = itemView.findViewById(R.id.item_order_title);
            price = itemView.findViewById(R.id.item_order_sprice);
            status = itemView.findViewById(R.id.item_order_status);


        }
    }
}
