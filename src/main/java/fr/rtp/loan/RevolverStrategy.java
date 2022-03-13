package fr.rtp.loan;

import java.util.Date;

public class RevolverStrategy extends CapitalStrategy{
    public RevolverStrategy(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        super(commitment, outstanding, start, expiry, maturity, riskRating);
    }

    double capital(Loan loan) {
        return getCommitment() * getUnusedPercentage() * duration(loan) * riskFactor();
    }

    double duration(Loan loan) {
        return this.yearsTo(this.getExpiry());
    }
}
