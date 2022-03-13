package fr.rtp.loan;

import java.util.Date;

public abstract class CapitalStrategy {

    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;

    private Date today;

    private final double commitment;
    private final int riskRating;
    private final double outstanding;
    private final Date maturity;
    private final Date expiry;
    private final Date start;

    public CapitalStrategy(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
        this.riskRating = riskRating;
    }

    protected double yearsTo(Date endDate) {
        Date beginDate = (today == null ? getStart() : today);
        return ((double) (endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
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

    double outstandingRiskAmount() {
        return getOutstanding();
    }

    double riskFactor() {
        return RiskFactor.getFactors().forRating(getRiskRating());
    }

    double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(getRiskRating());
    }

    double getUnusedPercentage() {
        return 0.05;
    }

    double unusedRiskAmount() {
        return (getCommitment() - getOutstanding());
    }

    abstract double capital(Loan loan);
    abstract double duration(Loan loan);

}
