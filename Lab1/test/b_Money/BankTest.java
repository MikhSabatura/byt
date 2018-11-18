package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
    Currency SEK, DKK;
    Bank SweBank, Nordea, DanskeBank;

    @Before
    public void setUp() throws Exception {
        DKK = new Currency("DKK", 0.20);
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        Nordea = new Bank("Nordea", SEK);
        DanskeBank = new Bank("DanskeBank", DKK);
        SweBank.openAccount("Ulrika");
        SweBank.openAccount("Bob");
        Nordea.openAccount("Bob");
        DanskeBank.openAccount("Gertrud");
    }

    @Test
    public void testGetName() {
        assertEquals("SweBank", SweBank.getName());
        assertEquals("Nordea", Nordea.getName());
        assertEquals("DanskeBank", DanskeBank.getName());
    }

    @Test
    public void testGetCurrency() {
        assertEquals(DKK, DanskeBank.getCurrency());
        assertEquals(SEK, SweBank.getCurrency());
        assertEquals(SEK, Nordea.getCurrency());
    }

    //failed: opening an already-existent account didn't cause an exception
    @Test
    public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
        try {
            Nordea.openAccount("Angela");
            SweBank.openAccount("Steve");
            DanskeBank.openAccount("Bob");
        } catch (AccountExistsException e) {
            /*
             * checking if creating a new account causes an exception
             * */
            fail("Should not throw an exception");
        }
        /*
         * checking if opening an already-existent account causes an exception
         * */
        try {
            SweBank.openAccount("Ulrika");
            Nordea.openAccount("Bob");
            DanskeBank.openAccount("Gertrud");
            fail("Should throw an exception");
        } catch (AccountExistsException e) {
        }
    }

    //failed because of null-pointer exception
    /*assert that the method throws an AccountDoesNotExistException*/
    @Test(expected = AccountDoesNotExistException.class)
    public void testDeposit() throws AccountDoesNotExistException {
        try {
            Nordea.deposit("Bob", new Money(1000, SEK));
            SweBank.deposit("Bob", new Money(1000, SEK));
            DanskeBank.deposit("Gertrud", new Money(1000, DKK));
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if depositing to existent accounts causes an exception
             * */
            fail("Should not throw an exception");
        }
        /*
         * checking if depositing to a non-existent account throws an exception
         * */
        Nordea.deposit("NonExistentAcc", new Money(1000, SEK));
        SweBank.deposit("NonExistentAcc", new Money(1000, SEK));
        DanskeBank.deposit("NonExistentAcc", new Money(1000, DKK));
    }

    //failed: withdrawing from an existent account throwed an exception
    /*assert that the method throws an AccountDoesNotExistException*/
    @Test(expected = AccountDoesNotExistException.class)
    public void testWithdraw() throws AccountDoesNotExistException {
        try {
            Nordea.withdraw("Bob", new Money(1000, SEK));
            SweBank.withdraw("Bob", new Money(1000, SEK));
            DanskeBank.withdraw("Gertrud", new Money(1000, DKK));
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if withdrawing from existent accounts throws an exception
             * */
            fail("Should not throw an exception");
        }
        /*
         * checking if withdrawing from a non-existent account throws an exception
         * */
        Nordea.withdraw("NonExistentAcc", new Money(1000, SEK));
        SweBank.withdraw("NonExistentAcc", new Money(1000, SEK));
        DanskeBank.withdraw("NonExistentAcc", new Money(1000, DKK));
    }

    //failed: getting balance of existent accounts throws an exception
    @Test(expected = AccountDoesNotExistException.class)
    public void testGetBalance() throws AccountDoesNotExistException {
        try {
            /*
             * checking if the returned balance is null
             * */
            String msg = "Should not be null";
            assertNotNull(msg, SweBank.getBalance("Bob"));
            assertNotNull(msg, Nordea.getBalance("Bob"));
            assertNotNull(msg, DanskeBank.getBalance("Gertrud"));
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if getting balance of existent accounts throws an exception
             * */
            fail("Should not throw an exception");
        }
        SweBank.getBalance("NonExistentAcc");
        Nordea.getBalance("NonExistentAcc");
        DanskeBank.getBalance("NonExistentAcc");
    }

    //failed because of null-pointer exception
    @Test(expected = AccountDoesNotExistException.class)
    public void testTransfer() throws AccountDoesNotExistException {
        try {
            SweBank.transfer("Bob", Nordea, "Bob", new Money(1000, SEK));
            SweBank.transfer("Bob", SweBank, "Bob", new Money(1000, SEK));
            DanskeBank.transfer("Gertrud", SweBank, "Ulrika", new Money(1000, DKK));
            SweBank.transfer("Bob", "Ulrika", new Money(1000, DKK));
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if performing transfer between existent accounts throws an exception
             * */
            e.printStackTrace();
            fail("Should not throw an exception");
        }
        SweBank.transfer("NonExistentAcc", "Bob", new Money(1000, SEK));
        DanskeBank.transfer("NonExistentAcc", SweBank, "Ulrika", new Money(1000, DKK));
    }

    //failed because of null-pointer exception - the tested method didn't throw appropriate exception when the account didn't exist
    /*asserting that AccountDoesNotExistException is thrown*/
    @Test(expected = AccountDoesNotExistException.class)
    public void testTimedPayment() throws AccountDoesNotExistException {
        try {
            /*
             * adding a valid timed payment and checking if any exceptions are thrown
             * */
            SweBank.addTimedPayment("Bob", "BobInsurance", 5, 5, new Money(1000, SEK), DanskeBank, "Gertrud");
            for (int i = 0; i < 10; i++) {
                SweBank.tick();
            }
        } catch (AccountDoesNotExistException e) {
            fail("Should not throw an exception");
        }
        /*
         * adding a timed payment from a non-existent account
         * */
        DanskeBank.addTimedPayment("NonExistentAcc", "Insurance", 1, 0, new Money(1000, SEK), Nordea, "Bob");
        DanskeBank.tick();
    }
}
