package com.example.myshoppingapp.adapters;

import android.annotation.SuppressLint;
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
import com.example.myshoppingapp.activities.ViewAllActivity;
import com.example.myshoppingapp.models.HomeCategory;
import com.example.myshoppingapp.models.RecommendedModel;

import java.util.List;


public class HomeAdapterRecommended extends RecyclerView.Adapter<HomeAdapterRecommended.ViewHolder> {
    private Context context;
    private List<RecommendedModel> recommendedModelList;


    public HomeAdapterRecommended(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList =recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        Glide.with(context).load(recommendedModelList.get(position).getImage_url()).into(holder.catImg);
        holder.name.setText(recommendedModelList.get(position).getName());
        holder.description.setText(recommendedModelList.get(position).getDescription());
        holder.rating.setText(recommendedModelList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, ViewAllActivity.class);
                    intent.putExtra("type", recommendedModelList.get(clickedPosition).getType());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView name,description,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.rec_img);
            name=itemView.findViewById(R.id.rec_name);
            description=itemView.findViewById(R.id.rec_description);
            rating=itemView.findViewById(R.id.rec_rating);
        }
    }}
