package fr.rtp.loan;

public class CapitalStrategy {
    private final double commitment;

    public CapitalStrategy(double commitment) {
        this.commitment = commitment;
    }

    protected double getCommitment() {
        return commitment;
    }
}
