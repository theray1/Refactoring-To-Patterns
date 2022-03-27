package fr.rtp.creation.creationmethods;

import java.util.Date;

public class Loan {

    double commitment;
    double outstanding;
    int riskRating;
    Date maturity;
    Date expiry;
    CapitalStrategy capitalStrategy;

    private Loan(double commitment, int riskRating, Date maturity) {
        this(null, commitment, 0.00, riskRating, maturity, null);
    }

    private Loan(CapitalStrategy capitalStrategy, double commitment, int riskRating, Date maturity, Date expiry) {
        this(capitalStrategy, commitment, 0.00, riskRating, maturity, expiry);
    }

    private Loan(CapitalStrategy capitalStrategy, double commitment, double outstanding, int riskRating, Date maturity,
                Date expiry) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.riskRating = riskRating;
        this.maturity = maturity;
        this.expiry = expiry;
        this.capitalStrategy = capitalStrategy;

        if (capitalStrategy == null) {
            if (expiry == null)
                this.capitalStrategy = new CapitalStrategyTermLoan();
            else if (maturity == null)
                this.capitalStrategy = new CapitalStrategyRevolver();
            else
                this.capitalStrategy = new CapitalStrategyRCTL();
        }
    }

    public static Loan createTermLoan(Date maturity, int riskRating, double commitment) {
        return new Loan(null, commitment, 0.00, riskRating, maturity, null);
    }

    public static Loan newRevolver(Date maturity, Date expiry, int riskRating, double commitment) {
        return new Loan(null, commitment, 0.00, riskRating, maturity, expiry);
    }

    public static Loan newAdvisedLine(Date maturity, Date expiry, int riskRating, double commitment, double outstanding) {
        return new Loan(null, commitment, outstanding, riskRating, maturity, expiry);
    }
}
