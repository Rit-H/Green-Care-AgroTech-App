package com.exampleee.agroapk.frags;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.NotificationAdapter;
import com.exampleee.agroapk.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {

    View layout;

    Activity activity;

    public NotificationFragment(Activity activity) {
        this.activity = activity;
    }

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_notification,container,false);

        recyclerView = layout.findViewById(R.id.recycler);

        loadData();

        return layout;
    }

    private void loadData() {

        List<Notification> list = new ArrayList<>();
//        list.add(new Notification("","","","","",true));
//        list.add(new Notification("","","","","",true));
//        list.add(new Notification("","","","","",false));
//



        NotificationAdapter adapter = new NotificationAdapter(list, layout.getContext());
        recyclerView.setAdapter(adapter);

    }
}