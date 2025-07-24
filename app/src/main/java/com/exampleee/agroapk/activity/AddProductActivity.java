//package com.example.agroapk.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.agroapk.R;
//import com.example.agroapk.models.Products;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class AddProductActivity extends AppCompatActivity {
//
//    EditText productIdEditText, productTitleEditText, productCoverEditText, productInfoCoverEditText,
//            productPriceEditText, productSpecialPriceEditText;
//    Button addButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_add_product);
//
//
//        ImageView companyImageImageView = findViewById(R.id.add_product_img);
//
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);
//
//// Apply animation to image view
//        companyImageImageView.startAnimation(animation);
//
//
//        productIdEditText = findViewById(R.id.edit_text_product_id);
//        productTitleEditText = findViewById(R.id.edit_text_product_title);
//        productCoverEditText = findViewById(R.id.edit_text_product_cover);
//        productInfoCoverEditText = findViewById(R.id.edit_text_product_info_cover);
//        productPriceEditText = findViewById(R.id.edit_text_product_price);
//        productSpecialPriceEditText = findViewById(R.id.edit_text_product_special_price);
//
//        addButton = findViewById(R.id.button_add_product);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addProductToFirebase();
//            }
//        });
//    }
//
//
//
//
//    private void addProductToFirebase() {
//        String id = productIdEditText.getText().toString();
//        String title = productTitleEditText.getText().toString();
//        String cover = productCoverEditText.getText().toString();
//        String infoCover = productInfoCoverEditText.getText().toString();
//        int price = Integer.parseInt(productPriceEditText.getText().toString());
//        int sprice = Integer.parseInt(productSpecialPriceEditText.getText().toString());
//        String categoryId = "1";
//
//        // Get the reference to your Firebase database
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
//
//        // Push the product data to Firebase
//        String productId = databaseReference.push().getKey();
//        Products product = new Products(id, title, cover, price, sprice, infoCover, categoryId);
//        if (productId != null) {
//            databaseReference.child(productId).setValue(product);
//            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
//        }
//    }
//}

package com.exampleee.agroapk.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.exampleee.agroapk.R;
import com.exampleee.agroapk.models.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductActivity extends AppCompatActivity {

    EditText productIdEditText, productTitleEditText, productCoverEditText, productInfoCoverEditText,
            productPriceEditText, productSpecialPriceEditText;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_product);


        ImageView companyImageImageView = findViewById(R.id.add_product_img);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_from_bottom);

// Apply animation to image view
        companyImageImageView.startAnimation(animation);


        productIdEditText = findViewById(R.id.edit_text_product_id);
        productTitleEditText = findViewById(R.id.edit_text_product_title);
        productCoverEditText = findViewById(R.id.edit_text_product_cover);
        productInfoCoverEditText = findViewById(R.id.edit_text_product_info_cover);
        productPriceEditText = findViewById(R.id.edit_text_product_price);
        productSpecialPriceEditText = findViewById(R.id.edit_text_product_special_price);

        addButton = findViewById(R.id.button_add_product);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToFirebase();
            }
        });
    }




    private void addProductToFirebase() {
        String id = productIdEditText.getText().toString();
        String title = productTitleEditText.getText().toString();
        String cover = productCoverEditText.getText().toString();
        String infoCover = productInfoCoverEditText.getText().toString();
        int price = Integer.parseInt(productPriceEditText.getText().toString());
        int sprice = Integer.parseInt(productSpecialPriceEditText.getText().toString());
        String categoryId = "1";
        String description = "Description for agroapk product";

        // Get the reference to your Firebase database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

        // Push the product data to Firebase
        String productId = databaseReference.push().getKey();
        Products product = new Products(id, title, cover, price, sprice, infoCover, categoryId, description);
        if (productId != null) {
            databaseReference.child(productId).setValue(product);
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
        }
    }
}

