package com.example.f_manager;

public class farmDataList {
    private Integer farmId;
    private Integer userId;
    private String farmName,farmLocation;
    private  Double budgetAllotment;

    public farmDataList(Integer farmId, Integer userId, String farmName, String farmLocation, Double budgetAllotment) {
        this.farmId = farmId;
        this.userId = userId;
        this.farmName = farmName;
        this.farmLocation = farmLocation;
        this.budgetAllotment = budgetAllotment;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public Double getBudgetAllotment() {
        return budgetAllotment;
    }

    public void setBudgetAllotment(Double budgetAllotment) {
        this.budgetAllotment = budgetAllotment;
    }
}
