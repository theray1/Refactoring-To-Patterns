package fr.rtp.loan;

import java.util.Date;

public class Payment {
    private double amount;
    private Date date;

    public double amount() {
        return amount;
    }

    public Date date() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
