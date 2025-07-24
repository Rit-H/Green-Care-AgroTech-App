package com.exampleee.agroapk.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.exampleee.agroapk.models.Products;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public ProductAdapter(List<Products> list, boolean isVertical) {
        this.list = list;
        this.isVertical = isVertical;
    }

    List<Products> list;
    boolean isVertical;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;

        if (isVertical){
            viewHolder=new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false));

        }else{
            viewHolder = new ProductHolderHorizontal(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_horizontal, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder multiHolder, int position) {
        if (isVertical){
            ProductHolder holder= (ProductHolder) multiHolder;

            Products products = list.get(position);

            holder.title.setText(products.getTitle());


            // String price = "<del>₹"+products.getSprice()+"</del>";
            //String sprice = "₹"+products.getSprice();

//            String price = "<del>₹" + products.getPrice() + "</del>"; // Change this line
//            String sprice = "₹" + products.getSprice(); // No change needed




            //off formulla

//            int off_value =(int) (100 - (((double) products.getSprice() / products.getPrice()) * 100));
//            String off = off_value+ "%OFF";


//            holder.price.setText(Html.fromHtml(price));
//            holder.sprice.setText(Html.fromHtml(sprice));
//            holder.off.setText(off);

            Glide.with(holder.itemView.getContext())
                    .load(products.getCover())
                    .error(R.drawable.cover)
                    .into(holder.cover);


            holder.itemView.setOnClickListener(v -> {

                openProduct(products,holder.itemView.getContext());

            });


        }else {
            ProductHolderHorizontal holder = (ProductHolderHorizontal) multiHolder;
            Products products = list.get(position);

            holder.title.setText(products.getTitle());


            // String price = "<del>₹"+products.getPrice()+"</del>";
            // String Sprice = "₹"+products.getSprice();
//            String price = "<del>₹" + products.getPrice() + "</del>"; // No change needed
//            String Sprice = "₹" + products.getSprice(); // Change this line


            //if selling prise and actual prize same...
//            if (products.getSprice()>= products.getSprice()){
//                holder.off.setText("");


//            }else{
//                //off formulla
//                int off_value =(int) (100 - (((double) products.getSprice() / products.getPrice()) * 100));
//                String off = off_value+ "%OFF";
//                holder.off.setText(off);
//
//            }


//            holder.price.setText(Html.fromHtml(price));
//            holder.sprice.setText(Html.fromHtml(Sprice));

            Glide.with(holder.itemView.getContext())
                    .load(products.getCover())
                    .error(R.drawable.cover)
                    .into(holder.cover);


            holder.itemView.setOnClickListener(v -> {

                openProduct(products,holder.itemView.getContext());

            });


        }

    }

    private void openProduct(Products products, Context context) {
        // Set the product ID to the static variable in Products class
        Products.products_id = products.getId();

        // Create intent to start DetailsActivity and pass data
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("product_id", products.getId());
        intent.putExtra("product_name", products.getTitle());
        intent.putExtra("product_image_url", products.getCover());
        intent.putExtra("product_info_url", products.getInfovoverCover());
        intent.putExtra("description", products.getDescription());
//        intent.putExtra("product_price", products.getPrice());
//        intent.putExtra("product_discount", products.getSprice());

        // Start DetailsActivity with the intent
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public void insertData(List<Products> items){

        int positionStart = getItemCount();
        int item_count = items.size();
        this.list.addAll(items);
        notifyItemRangeInserted(positionStart,item_count);

    }

    public void clearAll(){

        this.list.clear();
        notifyDataSetChanged();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView title,price,sprice,off;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_product_cover);
            title = itemView.findViewById(R.id.item_product_title);
            price = itemView.findViewById(R.id.item_product_price);
            sprice = itemView.findViewById(R.id.item_product_sprice);
            off = itemView.findViewById(R.id.item_product_off);
        }
    }

    public static class ProductHolderHorizontal extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title,price,sprice,off;
        public ProductHolderHorizontal(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_product_cover);
            title = itemView.findViewById(R.id.item_product_title);
            price = itemView.findViewById(R.id.item_product_price);
            sprice = itemView.findViewById(R.id.item_product_sprice);
            off = itemView.findViewById(R.id.item_product_off);
        }
    }
}
