package com.example.f_manager;

public class MyData {
    private String cycleTitle;
    private String cycleDate;

    public MyData(String cycleTitle, String cycleDate) {
        this.cycleTitle = cycleTitle;
        this.cycleDate = cycleDate;
    }

    public void setCycleTitle(String cycleTitle) {
        this.cycleTitle = cycleTitle;
    }

    public void setCycleDate(String cycleDate) {
        this.cycleDate = cycleDate;
    }

    public String getCycleTitle() {
        return cycleTitle;
    }

    public String getCycleDate() {
        return cycleDate;
    }
}
