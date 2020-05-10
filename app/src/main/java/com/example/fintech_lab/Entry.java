package com.example.fintech_lab;

public class Entry {

    String currency;
    String rate;

    public Entry(String currency, String rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRate() {
        return rate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
