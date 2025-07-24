package com.account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.helper.Helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    ImageView imageProfile;
    EditText editName,editBio;

    Button btnUpdate;

    Uri uri;
    int PICK_IMAGE =1;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ImageView companyImageImageView = findViewById(R.id.edit_profile_logo_image);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);


        imageProfile  = findViewById(R.id.edit_user_profile);
        editName = findViewById(R.id.edit_user_name);
        editBio= findViewById(R.id.edit_user_bio);
        btnUpdate = findViewById(R.id.edit_user_update);

        btnUpdate.setOnClickListener(v -> {

            String name = editName.getText().toString();
            String bio = editBio.getText().toString();

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();

            if (name.isEmpty()) {

                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (uri != null){

                uploadImage(name,bio);
            }else {

                uploadDetails(name,bio);
            }

        });

        imageProfile.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Dexter.withContext(this).withPermission(Manifest.permission.READ_MEDIA_IMAGES).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        startChooser();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();

                    }
                }).check();

            } else {
                Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        startChooser();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }

                }).check();
            }

        });

        Helper.setTool("Edit Profile",EditProfileActivity.this);

    }

    private void startChooser() {
        String[] mines =new String[]{"image/jpg","image/jpeg","image/png"};
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mines);
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && data!= null && data.getData()!=null){
            uri = data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                Glide.with(EditProfileActivity.this).load(bitmap)
                .into(imageProfile);

            }catch (Exception e){

                uri = null;
                Toast.makeText(this,"Failed to select file",Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void uploadDetails(String name, String bio) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            mDatabase.child("users").child(userId).child("name").setValue(name);
            mDatabase.child("users").child(userId).child("bio").setValue(bio);
            Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImage(String name, String bio) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("profile_images").child(userId + ".jpg");
            storageRef.putFile(uri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Get the URL of the uploaded image
                        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();
                            // Update user's profile image URL in the database
                            mDatabase.child("users").child(userId).child("profileImageUrl").setValue(imageUrl);
                            // After uploading image, also upload details
                            uploadDetails(name, bio);
                        });
                    })
                    .addOnFailureListener(e -> {
                        // Handle unsuccessful uploads
                        Toast.makeText(EditProfileActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}