package com.example.f_manager;

public class MyData {
    private String cycleTitle;
    private String cycleDate;
    private String cycleEndDate;
    private String status;

    public MyData(String cycleTitle, String cycleDate,String cycleEnd_date,String Status) {
        this.cycleTitle = cycleTitle;
        this.cycleDate = cycleDate;
        this.cycleEndDate = cycleEnd_date;
        this.status = Status;
    }
    public String getCycleEndDate() {
        return cycleEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCycleEndDate(String cycleEndDate) {
        this.cycleEndDate = cycleEndDate;
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
