package fr.rtp.loan;

public class RiskFactor {
    public static RiskFactor getFactors() {
        return new RiskFactor();
    }

    public double forRating(int riskRating) {
        return riskRating * 0.105;
    }
}
