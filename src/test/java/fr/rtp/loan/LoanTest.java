package fr.rtp.loan;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void termLoanCapital() {
        Date start = new Date(2020,1,1);
        Date maturity = new Date(2022, 12,31);

        Loan loan = new Loan(1000, start, maturity,5);
        loan.payment(100, new Date(2020,2,1));
        loan.payment(100, new Date(2020,3,1));
        loan.payment(100, new Date(2020,4,1));

        assertEquals(85.78, loan.capital(), 0.1);
    }

    @Test
    void revolverLoanCapital() {
        Date start = new Date(2020,1,1);
        Date maturity = null;
        Date expiry = new Date(2022, 12,31);

        Loan loan = new Loan(1000, 0.5, start, expiry, maturity, 5);

        loan.payment(100, new Date(2020,2,1));
        loan.payment(100, new Date(2020,3,1));
        loan.payment(100, new Date(2020,4,1));

        assertEquals(78.75, loan.capital(), 0.01);
    }

    @Test
    void duration() {
    }

    @Test
    void payment() {
    }
}
