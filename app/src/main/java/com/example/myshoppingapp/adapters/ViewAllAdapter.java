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
import com.example.myshoppingapp.activities.DetailedActivity;
import com.example.myshoppingapp.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {
    private Context context;
    private List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        Glide.with(context).load(viewAllModelList.get(position).getImage_url()).into(holder.viewImg);
        holder.name.setText(viewAllModelList.get(position).getName());
        holder.description.setText(viewAllModelList.get(position).getDescription());
        holder.rating.setText(viewAllModelList.get(position).getRating());
        holder.price.setText(viewAllModelList.get(position).getPrice()+"/kg");

//        if(viewAllModelList.get(position).getType().equals("egg")){
//            holder.price.setText(viewAllModelList.get(position).getPrice()+"/dozen");
//        }
//        if(viewAllModelList.get(position).getType().equals("juice")){
//            holder.price.setText(viewAllModelList.get(position).getPrice()+"/litre");
//        }
//        if(viewAllModelList.get(position).getType().equals("fish")){
//            holder.price.setText(viewAllModelList.get(position).getPrice()+"/Kg");
//        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, DetailedActivity.class);
                    intent.putExtra("detail", viewAllModelList.get(clickedPosition));
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView viewImg;

        TextView name,description,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg=itemView.findViewById(R.id.view_all_img);
            name=itemView.findViewById(R.id.view_all_name);
            description=itemView.findViewById(R.id.view_all_description);
            rating=itemView.findViewById(R.id.view_all_rating);
            price=itemView.findViewById(R.id.view_all_price);

        }
    }
}
