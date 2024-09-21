package com.example.f_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class farmAdapter extends RecyclerView.Adapter<farmAdapter.farmViewHolder> {
    private List<farmDataList> farm_list;
    private Context context;

    public farmAdapter(Context context,List<farmDataList> farmList) {
        this.context = context;
        this.farm_list = farmList;
    }

    public static class farmViewHolder extends  RecyclerView.ViewHolder{
        public TextView farmName,farmID,userID,farmLocation,farmBudgetAllotment;
        public farmViewHolder(@NonNull View itemView) {
            super(itemView);
            farmName = itemView.findViewById(R.id.farm_name);
            farmID = itemView.findViewById(R.id.farm_id);
            userID = itemView.findViewById(R.id.user_id);
            farmLocation = itemView.findViewById(R.id.farmLocation);
            farmBudgetAllotment = itemView.findViewById(R.id.farm_budget);
        }
    }

    @NonNull
    @Override
    public farmAdapter.farmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.farm_item_layout,parent,false);
        return new farmAdapter.farmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull farmAdapter.farmViewHolder holder, int position) {
        farmDataList dataItem = farm_list.get(position);
        holder.farmID.setText(String.valueOf(dataItem.getFarmId()));
        holder.farmName.setText(dataItem.getFarmName());
        holder.farmBudgetAllotment.setText(String.valueOf(dataItem.getBudgetAllotment()));
        holder.farmLocation.setText(dataItem.getFarmLocation());
        holder.userID.setText(String.valueOf(dataItem.getUserId()));
    }

    @Override
    public int getItemCount() {
        return farm_list.size();
    }


}
