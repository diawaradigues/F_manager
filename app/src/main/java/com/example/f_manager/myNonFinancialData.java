package com.example.f_manager;

public class myNonFinancialData {

    private Double productionYield;
    private Double foodConversionRatio;
    private Double mortalityRate;
    private Double averageDailyGain;
    private Double waterConsumption;
    private Double breakEvenPoint;
    public myNonFinancialData(Double production_yield,Double foodConversion_ratio,Double mortality_rate,Double adg,
                              Double water_consumption,Double bep){
        this.productionYield = production_yield;
        this.foodConversionRatio = foodConversion_ratio;
        this.mortalityRate = mortality_rate;
        this.averageDailyGain = adg;
        this.waterConsumption = water_consumption;
        this.breakEvenPoint = bep;
    }

    public Double getProductionYield() {
        return productionYield;
    }

    public void setProductionYield(Double productionYield) {
        this.productionYield = productionYield;
    }

    public Double getFoodConversionRatio() {
        return foodConversionRatio;
    }

    public void setFoodConversionRatio(Double foodConversionRatio) {
        this.foodConversionRatio = foodConversionRatio;
    }

    public Double getMortalityRate() {
        return mortalityRate;
    }

    public void setMortalityRate(Double mortalityRate) {
        this.mortalityRate = mortalityRate;
    }

    public Double getAverageDailyGain() {
        return averageDailyGain;
    }

    public void setAverageDailyGain(Double averageDailyGain) {
        this.averageDailyGain = averageDailyGain;
    }

    public Double getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(Double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public Double getBreakEvenPoint() {
        return breakEvenPoint;
    }

    public void setBreakEvenPoint(Double breakEvenPoint) {
        this.breakEvenPoint = breakEvenPoint;
    }
}
