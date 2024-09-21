package com.example.f_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CyclesEndedRecyclerAdapter extends RecyclerView.Adapter<CyclesEndedRecyclerAdapter.MyViewHolder> {
    private List<MyData> itemList;
    private Context context;

    //views from the item xml file
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView endedTitle,endedDescription,endedStatus;

        public MyViewHolder(View view) {
            super(view);
            endedTitle = view.findViewById(R.id.endedCycleRecycler_title);
            endedDescription=view.findViewById(R.id.endedcycleRecycler_description);
            endedStatus=view.findViewById(R.id.endedcycleRecycler_status);
        }
    }
    ///constructor
    public CyclesEndedRecyclerAdapter(Context context,List<MyData> myDataList) {
        this.context = context;
        this.itemList=myDataList;
    }
    //the oncreate method for the class
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cycles_ended_recyclerview_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyData item = itemList.get(position);
        holder.endedTitle.setText(item.getCycleTitle());
        holder.endedDescription.setText(item.getCycleEndDate());
        holder.endedStatus.setText(item.getStatus());
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
