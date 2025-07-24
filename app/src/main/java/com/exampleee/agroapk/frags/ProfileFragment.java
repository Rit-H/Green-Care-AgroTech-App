//package com.example.agroapk.frags;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.account.EditProfileActivity;
//import com.example.agroapk.R;
//import com.example.agroapk.activity.About_Us_Activity;
//import com.example.agroapk.activity.AddressActivity;
//import com.example.agroapk.activity.Address_map_activity;
//import com.example.agroapk.activity.AdminHomeActivity;
//import com.example.agroapk.activity.AdminLoginActivity;
//import com.example.agroapk.activity.CartsActivety;
//import com.example.agroapk.activity.LoginActivity;
//import com.example.agroapk.activity.NotificationDetailsActivity;
//import com.example.agroapk.activity.OrderActivity;
//import com.example.agroapk.activity.Review_Activity;
//import com.example.agroapk.activity.Social_Media_Activity;
//import com.example.agroapk.api.Const;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.bumptech.glide.Glide;
//
//public class ProfileFragment extends Fragment {
//
//    View layout;
//
//    Activity activity;
//
//    public ProfileFragment(Activity activity) {
//        this.activity = activity;
//    }
//    TextView btnEdit, btnLogout;
//
//   // LinearLayout actionOrder, actionCart, actionAddress, actionShare, actionRate, actionPolicy,actionReview, actionAdmin,actionNotification;
//
//    LinearLayout  actionAddress, actionShare, actionRate, actionPolicy,actionReview, actionAdmin,actionNotification,actionSocialMedia;
//
//    TextView txtName, txtEmail;
//    ImageView imgPofile;
//
//
//
//    // Firebase variables
//    private FirebaseAuth mAuth;
//    private DatabaseReference mDatabase;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        layout = inflater.inflate(R.layout.fragment_profile, container, false);
//
//        txtName = layout.findViewById(R.id.profile_name);
//        txtEmail = layout.findViewById(R.id.profile_email);
//        imgPofile = layout.findViewById(R.id.profile_pic);
//
//        btnEdit = layout.findViewById(R.id.profile_button_edit);
//        btnLogout = layout.findViewById(R.id.profile_button_logout);
//
////        actionOrder = layout.findViewById(R.id.profile_action_order);
////        actionCart = layout.findViewById(R.id.profile_action_cart);
//        ImageView socialMediaLogo1 = layout.findViewById(R.id.social_media_logo_1);
//        ImageView socialMediaLogo2 = layout.findViewById(R.id.social_media_logo_2);
//        ImageView socialMediaLogo3 = layout.findViewById(R.id.social_media_logo_3);
//        actionSocialMedia =layout.findViewById(R.id.profile_action_SocialMedia);
//        actionAddress = layout.findViewById(R.id.profile_action_address);
//        actionShare = layout.findViewById(R.id.profile_action_share);
//        actionRate = layout.findViewById(R.id.profile_action_rate);
//        actionPolicy = layout.findViewById(R.id.profile_action_policy);
//        actionReview = layout.findViewById(R.id.profile_action_Review);
//       // actionNotification = layout.findViewById(R.id.profile_action_Notification);
//        actionAdmin = layout.findViewById(R.id.profile_action_admin);
//
//
//        // Initialize Firebase variables
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        // Load user profile data
//        loadUserProfile();
//
//        // Button click listeners
//
//        actionSocialMedia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Social_Media_Activity.class));
//            }
//        });
//
//        socialMediaLogo1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open Instagram
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.instagram.com/greencare_agro.tech?igsh=M25wMndzbGhybW1p"));
//                startActivity(intent);
//            }
//        });
//
//// Social Media Logo 2 click listener
//        socialMediaLogo2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open YouTube
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://youtube.com/@greencare5577?feature=shared"));
//                startActivity(intent);
//            }
//        });
//
//
//// Social Media Logo 3 click listener
//        socialMediaLogo3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open Facebook
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100057260065792"));
//                startActivity(intent);
//            }
//        });
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), EditProfileActivity.class));
//            }
//        });
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(layout.getContext());
//                alert.setTitle("Logout..?")
//                        .setMessage("Are you sure want to logout..?")
//                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                FirebaseAuth auth = FirebaseAuth.getInstance();
//                                auth.signOut();
//                                Intent intent = new Intent(ProfileFragment.this.getActivity(), LoginActivity.class);
//                                startActivity(intent);
//                            }
//                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//            }
//        });
//
////        actionOrder.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(layout.getContext(), OrderActivity.class));
////            }
////        });
//
////        actionCart.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(layout.getContext(), CartsActivety.class));
////            }
////        });
//
//        actionAddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Address_map_activity.class));
//            }
//        });
//
//        actionReview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Review_Activity.class));
//            }
//        });
//
////        actionNotification.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(layout.getContext(), NotificationDetailsActivity.class));
////            }
////        });
//
//        actionSocialMedia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Social_Media_Activity.class));
//            }
//        });
//        actionAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), AdminLoginActivity.class));
//            }
//        });
//
////        actionShare.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String msg = "Hey Buddy Download Agricultural App:\n\n" +
////                        "App Link : https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName();
////                Intent intent = new Intent(Intent.ACTION_SEND);
////                intent.setType("text/plain");
////                intent.putExtra(Intent.EXTRA_TEXT, "");
////                intent.putExtra(Intent.EXTRA_SUBJECT, "SHARE APP");
////                startActivity(Intent.createChooser(intent, "AGRO APP"));
////            }
//
////        actionShare.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                String msg = "Hey Buddy Download Agricultural App:\n\n" +
////                        "App Link : https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName();
////
////
////                // "App Link : https://play.google.com/store/apps/details?id=com.king.candycrushsaga";
////
////
////                Intent intent = new Intent(Intent.ACTION_SEND);
////                intent.setType("text/plain");
////                //intent.putExtra(Intent.EXTRA_TEXT,msg);
////                intent.putExtra(Intent.EXTRA_TEXT, "");
////                intent.putExtra(Intent.EXTRA_SUBJECT, "SHARE APP");
////                startActivity(Intent.createChooser(intent, "AGRO APP"));
////            }
////
////            });
//
//        actionShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String msg = "Hey Buddy Download Agricultural App:\n\n" +
//                        "App Link : https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName();
//
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, msg); // Set the message content
//                intent.putExtra(Intent.EXTRA_SUBJECT, "SHARE APP");
//
//                // Make sure to use the correct context
//                startActivity(Intent.createChooser(intent, "AGRO APP"));
//            }
//        });
//
//
//        actionRate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String packageName = layout.getContext().getPackageName();
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details=" + packageName)));
//                } catch (Exception e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName())));
//                }
//            }
//        });
//
//        actionPolicy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Const.PRIVACY_POLICY)));
//                } catch (Exception e) {
//                    Toast.makeText(activity, "Invalid Link", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        return layout;
//    }
//
//    private void loadUserProfile() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            String userId = user.getUid();
//            String email = user.getEmail();
//            txtEmail.setText(email); // Set user's email
//            mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        String name = dataSnapshot.child("name").getValue(String.class);
//                        String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
//                        txtName.setText(name);
//                        // Load profile image using Glide or any other image loading library
//                        // Assuming you have Glide dependency added in your project
//                        Glide.with(activity)
//                                .load(profileImageUrl)
//                                .into(imgPofile);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle error
//                    Toast.makeText(activity, "Failed to load user profile", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}

package com.exampleee.agroapk.frags;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.account.EditProfileActivity;
import com.exampleee.agroapk.R;
import com.exampleee.agroapk.activity.Address_map_activity;
import com.exampleee.agroapk.activity.AdminLoginActivity;
import com.exampleee.agroapk.activity.LoginActivity;
import com.exampleee.agroapk.activity.Review_Activity;
import com.exampleee.agroapk.api.Const;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.bumptech.glide.Glide;

public class
ProfileFragment extends Fragment {

    View layout;

    Activity activity;

    public ProfileFragment(Activity activity) {
        this.activity = activity;
    }
    TextView btnEdit, btnLogout;

    LinearLayout actionAddress, actionShare, actionRate, actionPolicy,actionReview, actionAdmin;//actionNotification,actionSocialMedia;

    TextView txtName, txtEmail;
    ImageView imgPofile;



    // Firebase variables
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_profile, container, false);

        txtName = layout.findViewById(R.id.profile_name);
        txtEmail = layout.findViewById(R.id.profile_email);
        imgPofile = layout.findViewById(R.id.profile_pic);

        btnEdit = layout.findViewById(R.id.profile_button_edit);
        btnLogout = layout.findViewById(R.id.profile_button_logout);

        ImageView socialMediaLogo1 = layout.findViewById(R.id.social_media_logo_1);
        ImageView socialMediaLogo2 = layout.findViewById(R.id.social_media_logo_2);
        ImageView socialMediaLogo3 = layout.findViewById(R.id.social_media_logo_3);
        ImageView socialMediaLogo4 = layout.findViewById(R.id.social_media_logo_4);
       // actionSocialMedia =layout.findViewById(R.id.profile_action_SocialMedia);
        actionAddress = layout.findViewById(R.id.profile_action_address);
        actionShare = layout.findViewById(R.id.profile_action_share);
        actionRate = layout.findViewById(R.id.profile_action_rate);
        actionPolicy = layout.findViewById(R.id.profile_action_policy);
        actionReview = layout.findViewById(R.id.profile_action_Review);
        actionAdmin = layout.findViewById(R.id.profile_action_admin);


        // Initialize Firebase variables
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Load user profile data
        loadUserProfile();

        // Button click listeners

//        actionSocialMedia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Social_Media_Activity.class));
//            }
//        });

        socialMediaLogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Instagram
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/greencare_agro.tech?igsh=M25wMndzbGhybW1p"));
                startActivity(intent);
            }
        });

        socialMediaLogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open YouTube
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtube.com/@greencare5577?feature=shared"));
                startActivity(intent);
            }
        });

        socialMediaLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Facebook
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100057260065792"));
                startActivity(intent);
            }
        });

        socialMediaLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(layout.getContext(), EditProfileActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(layout.getContext());
                alert.setTitle("Logout..?")
                        .setMessage("Are you sure want to logout..?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                Intent intent = new Intent(ProfileFragment.this.getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });


        actionAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(layout.getContext(), Address_map_activity.class));
            }
        });

        actionReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(layout.getContext(), Review_Activity.class));
            }
        });

//        actionSocialMedia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(layout.getContext(), Social_Media_Activity.class));
//            }
//        });
        actionAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(layout.getContext(), AdminLoginActivity.class));
            }
        });

        actionShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Hey Buddy Download Agricultural App:\n\n" +
                        "App Link : https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, msg); // Set the message content
                intent.putExtra(Intent.EXTRA_SUBJECT, "SHARE APP");

                // Make sure to use the correct context
                startActivity(Intent.createChooser(intent, "AGRO APP"));
            }
        });


        actionRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = layout.getContext().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details=" + packageName)));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/app/details?id=" + layout.getContext().getPackageName())));
                }
            }
        });

        actionPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Const.PRIVACY_POLICY)));
                } catch (Exception e) {
                    Toast.makeText(activity, "Invalid Link", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return layout;
    }

    private void loadUserProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            String email = user.getEmail();
            txtEmail.setText(email); // Set user's email
            mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                        txtName.setText(name);
                        // Load profile image using Glide or any other image loading library
                        // Assuming you have Glide dependency added in your project
                        Glide.with(activity)
                                .load(profileImageUrl)
                                .into(imgPofile);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle error
                    Toast.makeText(activity, "Failed to load user profile", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    private void sendEmail() {
//        // Your email address
//        String email = "Greencareagrotech@gmail.com";
//
//        // Create an intent to send email
//        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//        emailIntent.setData(Uri.parse("mailto:" + email));
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject"); // You can set a default subject if needed
//
//        // Check if there's an email client available to handle this intent
//        if (emailIntent.resolveActivity(activity.getPackageManager()) != null) {
//            startActivity(emailIntent);
//        } else {
//            Toast.makeText(activity, "No email  found...Try again later", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void sendEmail() {
        // Create an intent to send email
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:Greencareagrotech@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject"); // You can set a default subject if needed

        // Start the activity for sending email
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }




}

