package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    Currency SEK, DKK;
    Bank Nordea;
    Bank DanskeBank;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);

        SweBank = new Bank("SweBank", SEK);
        Nordea = new Bank("Nordea", SEK);
        DanskeBank = new Bank("DanskeBank", DKK);

        SweBank.openAccount("Alice");
        testAccount = new Account("Hans", SEK);

        testAccount.deposit(new Money(10000000, SEK));
        SweBank.deposit("Alice", new Money(1000000, SEK)); //all tests failed because this line threw null-pointer exception
    }

    @Test
    public void testAddRemoveTimedPayment() {
        /*
         * checking if the methods work without throwing exceptions
         * */
        testAccount.addTimedPayment("Insurance", 1, 1, new Money(100, SEK), SweBank, "Alice");
        testAccount.removeTimedPayment("Insurance");
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        int interval = 2;
        int next = 2;
        /*
         * getting the initial account balance
         * */
        Money expected = testAccount.getBalance();
        Money withdrawn = new Money(100, SEK);
        testAccount.addTimedPayment("Insurance", interval, next, withdrawn, SweBank, "Alice");
        /*
         * performing timed payment 10 times
         * */
        for (int i = 0; i < 10; i++) {
            testAccount.tick();
            if (next == 0) {
                next = interval;
                expected = expected.sub(withdrawn); // decreasing the initial balance by the withdrawn amount
            } else {
                next -= 1;
            }
        }
        /*checking if the amount of withdrawn money is correct*/
        assertEquals(expected, testAccount.getBalance());
    }

    @Test
    public void testAddWithdraw() {
        /*
         * checking if:
         * amountAfterWithdrawal = initialBalance - withdrawnMoney
         * */
        Money initialBalance = testAccount.getBalance();
        Money withdrawn = new Money(100, SEK);
        testAccount.withdraw(withdrawn);
        assertEquals(initialBalance.sub(withdrawn), testAccount.getBalance());
    }

    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
        assertEquals(1000000, SweBank.getBalance("Alice").intValue());
    }
}
