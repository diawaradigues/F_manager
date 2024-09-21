package com.example.f_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class expendituresAdapter extends RecyclerView.Adapter<expendituresAdapter.ExpDataViewHolder>{
    private final List<expenditureDataList> expDataList;
    private Context context;
    public static class ExpDataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewExpCategory,textViewExpDescription,textViewAmount;
        public ExpDataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewExpCategory = itemView.findViewById(R.id.expCategory);
            textViewExpDescription = itemView.findViewById(R.id.expDescription);
            textViewAmount = itemView.findViewById(R.id.amount);
        }
    }
    public expendituresAdapter(Context context2,List<expenditureDataList> expItemList){
        this.context= context2;
        this.expDataList = expItemList;
    }

    @NonNull
    @Override
    public expendituresAdapter.ExpDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exp_item_adapter, parent, false);
        return new ExpDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull expendituresAdapter.ExpDataViewHolder holder, int position) {
        expenditureDataList dataItem = expDataList.get(position);
        holder.textViewExpCategory.setText(dataItem.getDirectAssetsCategory());
        holder.textViewExpDescription.setText(dataItem.getDirectAssetsDescription());
        holder.textViewAmount.setText(String.valueOf(dataItem.getDirectAssetsAmount()));
    }

    @Override
    public int getItemCount() {
        return expDataList.size();
    }
}
