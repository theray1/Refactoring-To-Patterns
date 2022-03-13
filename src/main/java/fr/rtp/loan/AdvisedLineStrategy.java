package fr.rtp.loan;

import java.util.Date;

public class AdvisedLineStrategy extends CapitalStrategy{
    public AdvisedLineStrategy(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        super(commitment, outstanding, start, expiry, maturity, riskRating);
    }

    double capital(Loan loan) {
        return (outstandingRiskAmount() * duration(loan) * riskFactor()) + (unusedRiskAmount() * duration(loan) * unusedRiskFactor());
    }

    double duration(Loan loan) {
        return this.yearsTo(this.getExpiry());
    }
}
