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
import com.example.myshoppingapp.models.PopularModels;

import java.util.List;




public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

     Context context;
     List<PopularModels> popularModelsList;

    public PopularAdapters(Context context, List<PopularModels> popularModelsList) {
        this.context = context;
        this. popularModelsList =  popularModelsList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularModelsList.get(position).getImage_url()).into(holder.popImg);
        holder.name.setText(popularModelsList.get(position).getName());
        holder.desc.setText(popularModelsList.get(position).getDesc());
        holder.discount.setText(popularModelsList.get(position).getDiscount());
        holder.rating.setText(popularModelsList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, ViewAllActivity.class);
                    intent.putExtra("type", popularModelsList.get(clickedPosition).getType());
                    context.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return popularModelsList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {


        ImageView popImg;
        TextView name,desc,rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popImg=itemView.findViewById(R.id.pop_img);
            name=itemView.findViewById(R.id.pop_name);
            desc=itemView.findViewById(R.id.pop_desc);
            rating=itemView.findViewById(R.id.pop_rating);
            discount=itemView.findViewById(R.id.pop_discount);
        }
    }
}

