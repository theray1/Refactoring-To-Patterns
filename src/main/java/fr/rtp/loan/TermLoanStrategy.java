package fr.rtp.loan;

import java.util.Date;

public class TermLoanStrategy extends CapitalStrategy{
    public TermLoanStrategy(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        super(commitment, outstanding, start, expiry, maturity, riskRating);
    }

    double capital(Loan loan) {
        return getCommitment() * duration(loan) * riskFactor();
    }

    double duration(Loan loan) {
        return loan.weightedAverageDuration();
    }
}
