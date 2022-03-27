package fr.rtp.creation.creationmethods;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class CapitalCalculationTest {

    @Test
    void testTermLoanNoPayments() {
        Date maturity = new Date(2024, 11, 31);
        int riskRating = 1;
        double commitment = 0.0;
        Loan termLoan = Loan.createTermLoan(maturity, riskRating, commitment);

        assert termLoan.capitalStrategy.getClass() == CapitalStrategyTermLoan.class;
    }

    @Test
    void testRevolvingCreditTermLoan() {
        Date maturity = new Date(2024, 11, 30);
        Date expiry = new Date(2020,  10, 15);
        int riskRating = 1;
        double commitment = 0.0;

        Loan revolvingCreditTermLoan  = Loan.newRevolver(maturity, expiry, riskRating, commitment);

        assert revolvingCreditTermLoan.capitalStrategy.getClass() == CapitalStrategyRCTL.class;
    }

    @Test
    void testRevolvingCreditTermLoanWithOutstanding() {
        Date maturity = new Date(2024, 11, 30);
        Date expiry = new Date(2020,  10, 15);
        int riskRating = 1;
        double commitment = 0.0;
        double outstanding = 2.0;

        Loan loan = Loan.newAdvisedLine(maturity, expiry, riskRating, commitment, outstanding);

        assert loan.capitalStrategy.getClass() == CapitalStrategyRCTL.class;
    }

}
