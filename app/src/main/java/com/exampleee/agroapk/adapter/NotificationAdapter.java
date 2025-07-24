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

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.NotificationDetailsActivity;
import com.exampleee.agroapk.models.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {



    List<Notification> list;
    Context context;

    public NotificationAdapter(List<Notification> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifications,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {

        Notification notification = list.get(position);

        holder.title.setText(notification.getTitle());
        holder.ago.setText(notification.getDate());

        holder.itemView.setOnClickListener(v ->{

            if (!notification.isRead()) {

                notification.setRead(true);
            }
            checkRead(holder.icon,notification);
            Notification.notification = notification;

            context.startActivity(new Intent(context, NotificationDetailsActivity.class));



        });

        checkRead(holder.icon,notification);

    }

    private void checkRead(ImageView icon, Notification notification) {

        if (notification.isRead()) {

            icon.setImageResource(R.drawable.notification_circle_read);
        }else {
            icon.setImageResource(R.drawable.notification_circle_unread);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NotificationHolder extends RecyclerView.ViewHolder{

        TextView title,ago;
        ImageView icon;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.item_notification_icon);

            title = itemView.findViewById(R.id.item_notification_title);
            ago = itemView.findViewById(R.id.item_notification_date);

        }
    }

}









/*
import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroapk.R;
import com.example.agroapk.models.Notification;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    public NotificationAdapter(List<Notification> list, Context context) {
        this.list = list;
        this.context = context;
    }

    List<Notification> list;
    Context context;

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notifications,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {

        Notification notification = list.get(position);

        holder.title.setText(notification.getTitle());
        holder.ago.setText(notification.getDate());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class NotificationHolder extends RecyclerView.ViewHolder{

        TextView title,ago;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.item_notification_title);
            ago=itemView.findViewById(R.id.item_notification_date);

        }
    }
}
*/