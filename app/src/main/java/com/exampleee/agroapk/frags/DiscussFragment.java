package com.exampleee.agroapk.frags;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.adapter.ConsultAdapter;
import com.exampleee.agroapk.models.Consult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DiscussFragment extends Fragment {



    View layout;

    Activity activity;

    DatabaseReference databaseReference;
    public DiscussFragment(Activity activity) {


        this.activity = activity;
    }

    RecyclerView recyclerView;
    ConsultAdapter adapter;
    List<Consult> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        layout = inflater.inflate(R.layout.fragment_discuss,container,false);

        recyclerView = layout.findViewById(R.id.recycler);
        adapter= new ConsultAdapter(list,layout.getContext());

        recyclerView.setAdapter(adapter);


        //Animationnnn
        ImageView companyImageImageView = layout.findViewById(R.id.discuss_decorative_image);
        Animation animation = AnimationUtils.loadAnimation(layout.getContext(), R.anim.slide_from_bottom);
        companyImageImageView.startAnimation(animation);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("consults");


        loadConsult();

        return layout;


    }

    private void loadConsult() {
        // Clear the existing list
        list.clear();

        // Attach a listener to read the data at the 'consults' reference
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again whenever data at this location changes.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convert each child of 'consults' into a Consult object
                    Consult consult = snapshot.getValue(Consult.class);
                    // Add the consult to the list
                    list.add(consult);
                }
                // Notify the adapter that the dataset has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}