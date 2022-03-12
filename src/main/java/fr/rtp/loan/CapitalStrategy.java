package fr.rtp.loan;

import java.util.Date;

public class CapitalStrategy {
    private final double commitment;
    private final int riskRating;
    private final double outstanding;
    private final Date maturity;
    private final Date expiry;
    private final Date start;

    public CapitalStrategy(double commitment, double outstanding, Date maturity, Date expiry, Date start, int riskRating) {
        this.commitment = commitment;
        this.riskRating = riskRating;
        this.outstanding = outstanding;
        this.maturity = maturity;
        this.expiry = expiry;
        this.start = start;
    }

    protected double getCommitment() {
        return commitment;
    }

    protected Date getMaturity() {
        return maturity;
    }

    protected Date getExpiry() {
        return expiry;
    }

    protected double getOutstanding() {
        return outstanding;
    }

    protected int getRiskRating() {
        return riskRating;
    }

    protected Date getStart() {
        return start;
    }
}
