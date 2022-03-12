package fr.rtp.loan;

import java.util.Date;

public class CapitalStrategy {

    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;

    private Date today;

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

    double duration(Loan loan) {
        if (this.getExpiry() == null && this.getMaturity() != null) // Term Loan
            return loan.weightedAverageDuration();
        else if (this.getExpiry() != null && this.getMaturity() == null) // Revolver or Advised Line
            return this.yearsTo(this.getExpiry());
        return 0.0;
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

    private double unusedRiskAmount() {
        return (getCommitment() - getOutstanding());
    }

    double capital(Loan loan) {
        if (getExpiry() == null && getMaturity() != null) // Term Loan
            return getCommitment() * duration(loan) * riskFactor();
        if (getExpiry() != null && getMaturity() == null) {
            if (getUnusedPercentage() != 1.0) // Revolver
                return getCommitment() * getUnusedPercentage() * duration(loan) * riskFactor();
            else // Advised Line
                return (outstandingRiskAmount() * duration(loan) * riskFactor())
                        + (unusedRiskAmount() * duration(loan) * unusedRiskFactor());
        }
        return 0.0;
    }
}
