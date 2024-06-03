package com.example.f_manager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<MyData> myDataList;

    public static class  CardViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public CardViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.recycler_title);
            descriptionTextView = itemView.findViewById(R.id.recycler_description);
        }
    }
    public CardAdapter(List<MyData> cardItemList){
        this.myDataList = cardItemList;

    }
    @NonNull
    @Override
    public  CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view,parent,false);
        return new CardViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position){
        MyData data_item = myDataList.get(position);
        holder.titleTextView.setText(data_item.getCycleTitle());
        holder.descriptionTextView.setText(data_item.getCycleDate());
    }
    @Override
    public int getItemCount(){
        return myDataList.size();
    }
}
