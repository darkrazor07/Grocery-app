package com.example.myshoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.NavCategoryDetailAdapter;
import com.example.myshoppingapp.models.NavCategoryDetailModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NavCategoryDetailModel> navCategoryDetailModelList;

    NavCategoryDetailAdapter navCategoryDetailAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db=FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView=findViewById(R.id.nav_cat_detail_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        navCategoryDetailModelList =new ArrayList<>();
        navCategoryDetailAdapter=new NavCategoryDetailAdapter(this, navCategoryDetailModelList);
        recyclerView.setAdapter(navCategoryDetailAdapter);

        if (type != null && type.equalsIgnoreCase("drink")) {
            db.collection("NewProducts").whereEqualTo("type", "drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailModel navCategoryDetailModel=documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);

                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
//
        if (type != null && type.equalsIgnoreCase("egg")) {
            db.collection("NewProducts").whereEqualTo("type", "egg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailModel navCategoryDetailModel=documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);

                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("fruit")) {
            db.collection("NewProducts").whereEqualTo("type", "fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailModel navCategoryDetailModel=documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);

                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
        if (type != null && type.equalsIgnoreCase("fish")) {
            db.collection("NewProducts").whereEqualTo("type", "fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        NavCategoryDetailModel navCategoryDetailModel=documentSnapshot.toObject(NavCategoryDetailModel.class);
                        navCategoryDetailModelList.add(navCategoryDetailModel);

                        navCategoryDetailAdapter.notifyDataSetChanged();
                    }
                }
            });
        }
//        if (type1 != null && type1.equalsIgnoreCase("cereals")) {
//            db.collection("NewProducts").whereEqualTo("type", "cereals").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
//                        NavCategoryDetailModel navCategoryDetailModel=documentSnapshot.toObject(NavCategoryDetailModel.class);
//                        list.add(navCategoryDetailModel);
//
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            });
//        }

    }
}