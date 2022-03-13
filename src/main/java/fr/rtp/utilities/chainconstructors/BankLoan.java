package fr.rtp.utilities.chainconstructors;

import java.util.Date;

public class BankLoan {

    BankCapitalStrategy strategy;
    float national;
    float outstanding;
    int rating;
    Date expiry;
    Date maturity;

    public BankLoan(float national, float outstanding, int rating, Date expiry) {
        this(new TermROC(), national, outstanding, rating, expiry, null);
    }

    public BankLoan(float national, float outstanding, int rating, Date expiry, Date maturity) {
        this(new RevolvingTermROC(), national, outstanding, rating, expiry, maturity);
    }

    public BankLoan(BankCapitalStrategy strategy, float national, float outstanding, int rating, Date expiry, Date maturity) {
        this.strategy = strategy;
        this.national = national;
        this.outstanding = outstanding;
        this.rating = rating;
        this.expiry = expiry;
        this.maturity = maturity;
    }
}
