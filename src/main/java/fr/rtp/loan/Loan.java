package fr.rtp.loan;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Loan {

    private static final int MILLIS_PER_DAY = 86400000;
    private static final int DAYS_PER_YEAR = 365;

    private int riskRating;
    private double commitment;
    private double outstanding;
    private Date maturity;
    private Date expiry;
    private Date today;
    private Date start;
    private Set<Payment> payments;
    private double unusedPercentage;

    private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.start = start;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.expiry = expiry;
        payments = new HashSet<Payment>();
    }

    public Loan(double commitment, Date start, Date maturity, int riskRating) {
        this.commitment = commitment;
        this.outstanding = commitment;
        this.start = start;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.expiry = null;
        payments = new HashSet<Payment>();
    }

    public double capital() {
        if (expiry == null && maturity != null) // Term Loan
            return commitment * duration() * riskFactor();
        if (expiry != null && maturity == null) {
            if (getUnusedPercentage() != 1.0) // Revolver
                return commitment * getUnusedPercentage() * duration() * riskFactor();
            else // Advised Line
                return (outstandingRiskAmount() * duration() * riskFactor())
                        + (unusedRiskAmount() * duration() * unusedRiskFactor());
        }
        return 0.0;
    }

    public double duration() {
        if (expiry == null && maturity != null) // Term Loan
            return weightedAverageDuration();
        else if (expiry != null && maturity == null) // Revolver or Advised Line
            return yearsTo(expiry);
        return 0.0;
    }

    private double outstandingRiskAmount() {
        return outstanding;
    }

    private double unusedRiskAmount() {
        return (commitment - outstanding);
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
        if (commitment != 0.0)
            duration = weightedAverage / sumOfPayments;
        return duration;
    }

    private double yearsTo(Date endDate) {
        Date beginDate = (today == null ? start : today);
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }

    private double riskFactor() {
        return RiskFactor.getFactors().forRating(riskRating);
    }

    private double unusedRiskFactor() {
        return UnusedRiskFactors.getFactors().forRating(riskRating);
    }

    private double getUnusedPercentage() {
        return 0.0;
    }

    public void payment(double amount, Date date) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(date);
        payments.add(payment);
    }
}
