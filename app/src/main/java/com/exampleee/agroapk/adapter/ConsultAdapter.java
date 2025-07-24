package com.exampleee.agroapk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.ConsultDetail;
import com.exampleee.agroapk.models.Consult;

import java.util.List;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ConsultHolder> {

    List<Consult> list;
    Context context;

    public ConsultAdapter(List<Consult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ConsultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConsultHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consult,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultHolder holder, int position) {

        Consult consult = list.get(position);

        Glide.with(context)
                .load(consult.getProfile())
                .error(R.drawable.sample_user)
                .placeholder(R.drawable.sample_user)
                .into(holder.profile);

        holder.name.setText(consult.getName());
        holder.occupation.setText(consult.getOccupation());
        holder.timing.setText(consult.getTiming());

        if (consult.getChat() != null && consult.getChat().length() >= 10) {
            holder.chat.setVisibility(View.VISIBLE);
            holder.chat.setOnClickListener(v -> {
                String countryCode = "91"; // Country code for India
                String phoneNumber = consult.getChat(); // Your phone number without the country code
                String whatsappLink = "https://wa.me/" + countryCode + phoneNumber;
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse(whatsappLink));
                context.startActivity(whatsappIntent);
            });
        } else {
            holder.chat.setVisibility(View.GONE);
        }

        if (consult.getChat() != null && consult.getChat().length() >= 10) {
            holder.call.setVisibility(View.VISIBLE);
            holder.call.setOnClickListener(v -> {
                String phoneNumber = consult.getChat(); // Your phone number
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                context.startActivity(callIntent);
            });
        } else {
            holder.call.setVisibility(View.GONE);
        }

        holder.details.setOnClickListener(v -> {
            launchDetails(consult,context);
        });

        holder.profile.setOnClickListener(v -> {
            launchDetails(consult,context);
        });
    }

    private void launchDetails(Consult consult, Context context) {
        Consult.consult = consult;
        context.startActivity(new Intent(context, ConsultDetail.class));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ConsultHolder extends RecyclerView.ViewHolder {

        ImageView profile, call, chat;
        TextView name, occupation, timing;
        LinearLayout details;

        public ConsultHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.item_consult_profile);
            call = itemView.findViewById(R.id.item_consult_call);
            chat = itemView.findViewById(R.id.item_consult_wa);
            name = itemView.findViewById(R.id.item_consult_name);
            occupation = itemView.findViewById(R.id.item_consult_occupation);
            timing = itemView.findViewById(R.id.item_consult_timing);
            details = itemView.findViewById(R.id.item_consult_details);
        }
    }
}
