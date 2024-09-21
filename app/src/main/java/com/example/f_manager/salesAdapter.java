package com.example.f_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class salesAdapter extends RecyclerView.Adapter<salesAdapter.DataViewHolder> {
    private List<salesDataList> myDataList;
    private Context context;
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCustomer,textViewQuantityOfBirds,textViewPrice;
        public DataViewHolder(View itemView) {
            super(itemView);
            textViewCustomer = itemView.findViewById(R.id.customer);
            textViewQuantityOfBirds = itemView.findViewById(R.id.qauntity_of_birds);
            textViewPrice = itemView.findViewById(R.id.price);
        }
    }
    public salesAdapter(Context context1,List<salesDataList> saleItemList) {
        this.context = context1;
        this.myDataList = saleItemList;
    }
    @Override
    @NonNull
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sales_item_adapter, parent, false);
        return new DataViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        salesDataList data_item = myDataList.get(position);
        holder.textViewCustomer.setText(data_item.getCustomer());
        holder.textViewQuantityOfBirds.setText(String.valueOf(data_item.getQuantityOfBirds()));
        holder.textViewPrice.setText(String.valueOf(data_item.getPrice()));
    }
    @Override
    public int getItemCount() {
        return myDataList.size();
    }
}

