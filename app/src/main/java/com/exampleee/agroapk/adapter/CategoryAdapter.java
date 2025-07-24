package com.exampleee.agroapk.adapter;

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
import com.exampleee.agroapk.activity.CategoryViewActivity;
import com.exampleee.agroapk.models.Category;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    List<Category> list;

    boolean full;

    public CategoryAdapter(List<Category> list, boolean full) {
        this.list = list;
        this.full = full;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (full){
            return new CategoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_full,parent,false));

        }else{
            return new CategoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = list.get(position);

        Glide.with(holder.itemView.getContext())
                .load(category.getCover())
                .placeholder(R.drawable.cover)
                .error(R.drawable.cover)
                .into( holder.cover);

        holder.title.setText(category.getTitle());
        holder.itemView.setOnClickListener(V->{
            Category.category = category;
            Intent intent = new Intent(holder.itemView.getContext(), CategoryViewActivity.class);
            intent.putExtra("categoryId", category.getId());
            holder.itemView.getContext().startActivity(intent);


        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder{
        ImageView cover;
        TextView title;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            cover=itemView.findViewById(R.id.item_category_cover);
            title=itemView.findViewById(R.id.item_category_title);
        }
    }
}
