package com.example.f_manager;

public class nonFinancialDataMetricsList {

    Double NumberOfBroilers;
    Double TimePeriod;
    Double TotalFeedConsumed;
    Double TotalWeightGain;
    Double NumberOfBroilersDied;
    Double TotalNumberOfBroilers;
    Double NumberOfDays;
    Double TotalWaterConsumed;
    Double SellingPricePerUnit;
    Double VariableCostPerUnit;
    public nonFinancialDataMetricsList(Double numberOfBroilers, Double timePeriod,
                                       Double totalFeedConsumed, Double totalWeightGain, Double numberOfBroilersDied,
                                       Double totalNumberOfBroilers, Double numberOfDays, Double totalWaterConsumed,
                                       Double sellingPricePerUnit, Double variableCostPerUnit) {
        NumberOfBroilers = numberOfBroilers;
        TimePeriod = timePeriod;
        TotalFeedConsumed = totalFeedConsumed;
        TotalWeightGain = totalWeightGain;
        NumberOfBroilersDied = numberOfBroilersDied;
        TotalNumberOfBroilers = totalNumberOfBroilers;
        NumberOfDays = numberOfDays;
        TotalWaterConsumed = totalWaterConsumed;
        SellingPricePerUnit = sellingPricePerUnit;
        VariableCostPerUnit = variableCostPerUnit;
    }

    public Double getNumberOfBroilers() {
        return NumberOfBroilers;
    }

    public void setNumberOfBroilers(Double numberOfBroilers) {
        NumberOfBroilers = numberOfBroilers;
    }

    public Double getTimePeriod() {
        return TimePeriod;
    }

    public void setTimePeriod(Double timePeriod) {
        TimePeriod = timePeriod;
    }

    public Double getTotalFeedConsumed() {
        return TotalFeedConsumed;
    }

    public void setTotalFeedConsumed(Double totalFeedConsumed) {
        TotalFeedConsumed = totalFeedConsumed;
    }

    public Double getTotalWeightGain() {
        return TotalWeightGain;
    }

    public void setTotalWeightGain(Double totalWeightGain) {
        TotalWeightGain = totalWeightGain;
    }

    public Double getNumberOfBroilersDied() {
        return NumberOfBroilersDied;
    }

    public void setNumberOfBroilersDied(Double numberOfBroilersDied) {
        NumberOfBroilersDied = numberOfBroilersDied;
    }

    public Double getTotalNumberOfBroilers() {
        return TotalNumberOfBroilers;
    }

    public void setTotalNumberOfBroilers(Double totalNumberOfBroilers) {
        TotalNumberOfBroilers = totalNumberOfBroilers;
    }

    public Double getNumberOfDays() {
        return NumberOfDays;
    }

    public void setNumberOfDays(Double numberOfDays) {
        NumberOfDays = numberOfDays;
    }

    public Double getTotalWaterConsumed() {
        return TotalWaterConsumed;
    }

    public void setTotalWaterConsumed(Double totalWaterConsumed) {
        TotalWaterConsumed = totalWaterConsumed;
    }

    public Double getSellingPricePerUnit() {
        return SellingPricePerUnit;
    }

    public void setSellingPricePerUnit(Double sellingPricePerUnit) {
        SellingPricePerUnit = sellingPricePerUnit;
    }

    public Double getVariableCostPerUnit() {
        return VariableCostPerUnit;
    }

    public void setVariableCostPerUnit(Double variableCostPerUnit) {
        VariableCostPerUnit = variableCostPerUnit;
    }
}
