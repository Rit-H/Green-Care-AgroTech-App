package com.exampleee.agroapk.activity;

import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Consult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddConsultActivity extends AppCompatActivity {



    EditText editTextName, editTextOccupation, editTextProfile, editTextChat, editTextCall, editTextTiming;
    Button buttonAddConsult;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consult);



        ImageView companyImageImageView = findViewById(R.id.add_consult_decorative_image);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("consults");

        editTextName = findViewById(R.id.editTextName);
        editTextOccupation = findViewById(R.id.editTextOccupation);
        editTextProfile = findViewById(R.id.editTextProfile);
        editTextChat = findViewById(R.id.editTextChat);
        editTextCall = findViewById(R.id.editTextCall);
        editTextTiming = findViewById(R.id.editTextTiming);
        buttonAddConsult = findViewById(R.id.buttonAddConsult);

        buttonAddConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String occupation = editTextOccupation.getText().toString().trim();
                String profile = editTextProfile.getText().toString().trim();
                String chat = editTextChat.getText().toString().trim();
                String call = editTextCall.getText().toString().trim();
                String timing = editTextTiming.getText().toString().trim();

                if (!name.isEmpty() && !occupation.isEmpty() && !profile.isEmpty() && !chat.isEmpty() && !call.isEmpty() && !timing.isEmpty()) {
                    addConsult(name, occupation, profile, chat, call, timing);
                }
            }
        });
    }

    private void addConsult(String name, String occupation, String profile, String chat, String call, String timing) {
        String id = databaseReference.push().getKey();
        Consult consult = new Consult(id, name, occupation, profile, chat, call, timing);
        databaseReference.child(id).setValue(consult);
        finish();
    }




}
