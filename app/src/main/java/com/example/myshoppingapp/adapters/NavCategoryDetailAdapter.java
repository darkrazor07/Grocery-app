package com.example.myshoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.activities.NavCategoryActivity;
import com.example.myshoppingapp.models.NavCategoryDetailModel;

import java.util.List;

public class NavCategoryDetailAdapter extends RecyclerView.Adapter<NavCategoryDetailAdapter.ViewHolder> {
    Context context;
    List<NavCategoryDetailModel> navCategoryDetailModelList;

    public NavCategoryDetailAdapter(Context context,  List<NavCategoryDetailModel> navCategortDetailModelList) {
        this.context = context;
        this.navCategoryDetailModelList = navCategortDetailModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(navCategoryDetailModelList.get(position).getImage_url()).into(holder.image_url);
        holder.name.setText(navCategoryDetailModelList.get(position).getName());
        holder.price.setText(navCategoryDetailModelList.get(position).getPrice());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int clickedPosition = holder.getAdapterPosition();
//                if (clickedPosition != RecyclerView.NO_POSITION) {
//                    Intent intent = new Intent(context, NavCategoryActivity.class);
//                    intent.putExtra("type", navCategoryDetailModelList.get(clickedPosition).getType());
//                    context.startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return navCategoryDetailModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_url;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_url=itemView.findViewById(R.id.nav_cat_img);
            price=itemView.findViewById(R.id.price);
            name=itemView.findViewById(R.id.nav_cat_name);
        }
    }
}
