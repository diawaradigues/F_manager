package com.example.f_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class nonFinancialmetricsAdapter extends RecyclerView.Adapter<nonFinancialmetricsAdapter.nonFinancialMetricsViewHolder> {
    private List<nonFinancialDataMetricsList> metricsDataList;
    private Context context;
    public static class nonFinancialMetricsViewHolder extends RecyclerView.ViewHolder{
        public TextView NumberOfBroilers,TimePeriod,TotalFeedConsumed,TotalWeightGain,NumberOfBroilersDied
        ,TotalNumberOfBroilers,NumberOfDays,TotalWaterConsumed,SellingPricePerUnit,VariableCostPerUnit;

        public nonFinancialMetricsViewHolder(@NonNull View itemView) {
            super(itemView);
            NumberOfBroilers = itemView.findViewById(R.id.number_of_broilers);
            TimePeriod = itemView.findViewById(R.id.time_period);
            TotalFeedConsumed = itemView.findViewById(R.id.total_feed_consumed);
            TotalWeightGain = itemView.findViewById(R.id.total_weight_Gain);
            NumberOfBroilersDied = itemView.findViewById(R.id.num_broilers_died);
            TotalNumberOfBroilers = itemView.findViewById(R.id.total_number_of_broilers);
            NumberOfDays = itemView.findViewById(R.id.number_of_days);
            TotalWaterConsumed = itemView.findViewById(R.id.total_water_consumed);
            SellingPricePerUnit = itemView.findViewById(R.id.selling_price_per_unit);
            VariableCostPerUnit = itemView.findViewById(R.id.variable_cost_per_unit);
        }
    }

    public nonFinancialmetricsAdapter(Context context,List<nonFinancialDataMetricsList> metricsDataList) {
        this.context = context;
        this.metricsDataList = metricsDataList;
    }

    @NonNull
    @Override
    public nonFinancialMetricsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.non_finacialdata_item,parent,false);
        return new nonFinancialMetricsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull nonFinancialMetricsViewHolder holder, int position) {
        nonFinancialDataMetricsList data = metricsDataList.get(position);
        holder.NumberOfBroilers.setText(String.valueOf(data.getNumberOfBroilers()));
        holder.TimePeriod.setText(String.valueOf(data.getTimePeriod()));
        holder.TotalFeedConsumed.setText(String.valueOf(data.getTotalFeedConsumed()));
        holder.TotalWeightGain.setText(String.valueOf(data.getTotalWeightGain()));
        holder.NumberOfBroilersDied.setText(String.valueOf(data.getNumberOfBroilersDied()));
        holder. TotalNumberOfBroilers.setText(String.valueOf(data.getTotalNumberOfBroilers()));
        holder.NumberOfDays.setText(String.valueOf(data.getNumberOfDays()));
        holder.TotalWaterConsumed.setText(String.valueOf(data.getTotalWaterConsumed()));
        holder.SellingPricePerUnit.setText(String.valueOf(data.getSellingPricePerUnit()));
        holder. VariableCostPerUnit.setText(String.valueOf(data.getVariableCostPerUnit()));
    }

    @Override
    public int getItemCount() {
        return metricsDataList.size();
    }
}
