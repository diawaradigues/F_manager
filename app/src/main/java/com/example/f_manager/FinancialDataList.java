package com.example.f_manager;

public class FinancialDataList {
    private Float totalSales,grossProfit,netProfit;

    public FinancialDataList(Float totalSales, Float grossProfit, Float netProfit) {
        this.totalSales = totalSales;
        this.grossProfit = grossProfit;
        this.netProfit = netProfit;
    }

    public Float getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Float totalSales) {
        this.totalSales = totalSales;
    }

    public Float getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Float grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Float getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Float netProfit) {
        this.netProfit = netProfit;
    }
}
