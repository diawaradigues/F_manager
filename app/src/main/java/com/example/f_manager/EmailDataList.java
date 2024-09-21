package com.example.f_manager;

public class EmailDataList {
    private String receipientEmail;

    public EmailDataList(String receipientEmail) {
        this.receipientEmail = receipientEmail;
    }

    public String getReceipientEmail() {
        return receipientEmail;
    }

    public void setReceipientEmail(String receipientEmail) {
        this.receipientEmail = receipientEmail;
    }
}
