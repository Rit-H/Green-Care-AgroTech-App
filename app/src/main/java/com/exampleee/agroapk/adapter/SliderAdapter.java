package com.exampleee.agroapk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Slider;
import com.smarteist.autoimageslider.SliderViewAdapter;
import androidx.annotation.NonNull;

import java.util.List;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    List<Slider> list;

    public SliderAdapter(List<Slider>list){

        this.list = list;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Slider slider=list.get(position);

        Glide.with(holder.itemView.getContext())
                .load(slider.getCover())
                .error(R.drawable.cover)
                .placeholder(R.drawable.cover)
                .into(holder.cover);

    }
    @Override
    public int getCount(){
        return list.size();
    }

    public static class Holder extends ViewHolder{

        ImageView cover;


        public Holder(@NonNull View itemView){
            super(itemView);
            cover = itemView.findViewById(R.id.item_slider_cover);
        }

    }
}
