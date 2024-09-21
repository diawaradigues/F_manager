package com.example.f_manager;

public class salesDataList {
    private String Customer;
    private Integer quantityOfBirds;
    private Double price;

    public salesDataList(String customer, Integer quantityOfBirds, Double price) {
        Customer = customer;
        this.quantityOfBirds = quantityOfBirds;
        this.price = price;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public Integer getQuantityOfBirds() {
        return quantityOfBirds;
    }

    public void setQuantityOfBirds(Integer quantityOfBirds) {
        this.quantityOfBirds = quantityOfBirds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
