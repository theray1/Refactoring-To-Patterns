package fr.rtp.loan;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Loan {

    private CapitalStrategy strategy;

    private Set<Payment> payments;
    private double unusedPercentage;

    public CapitalStrategy chooseStrategy(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating){
        if(expiry == null){
            return new TermLoanStrategy(commitment, outstanding, start, expiry, maturity, riskRating);
        }

        if(maturity == null){
            return new RevolverStrategy(commitment, outstanding, start, expiry, maturity, riskRating);
        }

        return new AdvisedLineStrategy(commitment, outstanding, start, expiry, maturity, riskRating);
    }

    public Loan(double commitment, int riskRating, Date maturity) {
        strategy = chooseStrategy(commitment, 0.00, null, null, maturity, riskRating);
    }

    public Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        strategy = chooseStrategy(commitment, outstanding, start, expiry, maturity, riskRating);
        payments = new HashSet<Payment>();
    }

    public Loan(double commitment, Date start, Date maturity, int riskRating) {
        strategy = chooseStrategy(commitment, commitment, start, null, maturity, riskRating);
        payments = new HashSet<Payment>();
    }

    public double capital() {
        return strategy.capital(this);
    }

    public double duration() {
        return strategy.duration(this);
    }

    protected double weightedAverageDuration() {
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
        return strategy.yearsTo(endDate);
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
