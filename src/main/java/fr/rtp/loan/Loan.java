package fr.rtp.loan;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Loan {

    private CapitalStrategy strategy;

    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;


    private Date today;

    private Set<Payment> payments;
    private double unusedPercentage;

    public Loan(double commitment, int riskRating, Date maturity) {
        strategy = new CapitalStrategy(commitment, 0.00, maturity, null, null, riskRating);
    }

    public Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        strategy = new CapitalStrategy(commitment, outstanding, maturity, expiry, start, riskRating);
        payments = new HashSet<Payment>();
    }

    public Loan(double commitment, Date start, Date maturity, int riskRating) {
        strategy = new CapitalStrategy(commitment, commitment, maturity, null, start, riskRating);

        payments = new HashSet<Payment>();
    }

    public double capital() {
        if (getExpiry() == null && getMaturity() != null) // Term Loan
            return getCommitment() * duration() * riskFactor();
        if (getExpiry() != null && getMaturity() == null) {
            if (getUnusedPercentage() != 1.0) // Revolver
                return getCommitment() * getUnusedPercentage() * duration() * riskFactor();
            else // Advised Line
                return (outstandingRiskAmount() * duration() * riskFactor())
                        + (unusedRiskAmount() * duration() * unusedRiskFactor());
        }
        return 0.0;
    }

    public double duration() {
        if (getExpiry() == null && getMaturity() != null) // Term Loan
            return weightedAverageDuration();
        else if (getExpiry() != null && getMaturity() == null) // Revolver or Advised Line
            return yearsTo(getExpiry());
        return 0.0;
    }

    private double outstandingRiskAmount() {
        return getOutstanding();
    }

    private double unusedRiskAmount() {
        return (getCommitment() - getOutstanding());
    }



    private double weightedAverageDuration() {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;
        Iterator loanPayments = payments.iterator();
        while (loanPayments.hasNext()) {
            Payment payment = (Payment) loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date()) * payment.amount();
        }
        if (getCommitment() != 0.0)
            duration = weightedAverage / sumOfPayments;
        return duration;
    }

    private double yearsTo(Date endDate) {
        Date beginDate = (today == null ? getStart() : today);
        return ((double) (endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }

    private double riskFactor() {
        return RiskFactor.getFactors().forRating(getRiskRating());
    }

    private double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(getRiskRating());
    }

    private double getUnusedPercentage() {
        return 0.05;
    }

    public void payment(double amount, Date date) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(date);
        payments.add(payment);
    }

    private double getCommitment() {
        return strategy.getCommitment();
    }

    private Date getMaturity() {
        return strategy.getMaturity();
    }

    private Date getExpiry() {
        return strategy.getExpiry();
    }

    private double getOutstanding() {
        return strategy.getOutstanding();
    }

    private int getRiskRating() {
        return strategy.getRiskRating();
    }

    private Date getStart() {
        return strategy.getStart();
    }
}
