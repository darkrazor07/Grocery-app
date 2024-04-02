package com.example.myshoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView imageView;
    int totalQuantity=1;
    int totalPrice;
    TextView price,rating,description,count;
    ImageView add,remove;
    Toolbar toolbar;
    Button cart;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ViewAllModel viewAllModel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel ){
            viewAllModel= (ViewAllModel)object;
        }
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        imageView=findViewById(R.id.detailed_img);
        add=findViewById(R.id.detailed_add);
        remove=findViewById(R.id.detailed_remove);
        rating=findViewById(R.id.detailed_rating);
        price=findViewById(R.id.detailed_price);
        description=findViewById(R.id.detailed_description);
        count=findViewById(R.id.detailed_count);
        cart=findViewById(R.id.detailed_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity<10){
                    totalQuantity++;
                    count.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;

                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity>1){
                    totalQuantity--;
                    count.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;

                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });


        if(viewAllModel!=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImage_url()).into(imageView);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText(viewAllModel.getPrice()+"/kg");
            totalPrice=viewAllModel.getPrice()*totalQuantity;
        }
//        if(viewAllModel.getType().equals("egg")){
//            price.setText(viewAllModel.getPrice()+"/dozen");
//        }
//        if(viewAllModel.getType().equals("milk")){
//
//            price.setText(viewAllModel.getPrice()+"/litre");
//        }
//        if(viewAllModel.getType().equals("juice")){
//
//            price.setText(viewAllModel.getPrice()+"/can");
//        }
//        if(viewAllModel.getType().equals("fish")){
//
//            price.setText(viewAllModel.getPrice()+"/kg");
//        }
    }

    private void addToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:MM:ss" );
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();
        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",count.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }
}