package com.example.myshoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshoppingapp.R;
import com.example.myshoppingapp.models.MyCartModel;

import org.w3c.dom.Text;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> myCartModelList;
    int totalPrice=0;

    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productName.setText(myCartModelList.get(position).getProductName());
        holder.productPrice.setText(myCartModelList.get(position).getProductPrice());
        holder.currentDate.setText(myCartModelList.get(position).getCurrentDate());
        holder.currentTime.setText(myCartModelList.get(position).getCurrentTime());
        holder.totalQuantity.setText(myCartModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()));

        totalPrice=totalPrice+myCartModelList.get(position).getTotalPrice();
        Intent intent=new Intent("totalAmount");
        intent.putExtra("totalBill",totalPrice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);



    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice,currentDate,currentTime,totalQuantity,totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            currentDate=itemView.findViewById(R.id.current_date);
            currentTime=itemView.findViewById(R.id.current_time);
            totalQuantity=itemView.findViewById(R.id.total_quantity);
            totalPrice=itemView.findViewById(R.id.total_price);
        }
    }
}
