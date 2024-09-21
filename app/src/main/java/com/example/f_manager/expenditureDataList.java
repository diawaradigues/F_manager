package com.example.f_manager;

public class expenditureDataList {
    private String directAssetsCategory, directAssetsDescription;
    private Double directAssetsAmount;
    public expenditureDataList(String directAssetsCategory, String directAssetsDescription, Double directAssetsAmount) {
        this.directAssetsCategory = directAssetsCategory;
        this.directAssetsDescription = directAssetsDescription;
        this.directAssetsAmount = directAssetsAmount;
    }

    public String getDirectAssetsCategory() {
        return directAssetsCategory;
    }

    public void setDirectAssetsCategory(String directAssetsCategory) {
        this.directAssetsCategory = directAssetsCategory;
    }

    public String getDirectAssetsDescription() {
        return directAssetsDescription;
    }

    public void setDirectAssetsDescription(String directAssetsDescription) {
        this.directAssetsDescription = directAssetsDescription;
    }

    public Double getDirectAssetsAmount() {
        return directAssetsAmount;
    }

    public void setDirectAssetsAmount(Double directAssetsAmount) {
        this.directAssetsAmount = directAssetsAmount;
    }
}
