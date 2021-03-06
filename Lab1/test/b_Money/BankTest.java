package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

            assertNotNull(Nordea.getBalance("Angela"));
            assertNotNull(SweBank.getBalance("Steve"));
            assertNotNull(DanskeBank.getBalance("Bob"));
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
            int initialBob = Nordea.getBalance("Bob");
            int initialUlrika = SweBank.getBalance("Ulrika");
            int initialGertrud = DanskeBank.getBalance("Gertrud");

            int depositAmount = 1000;
            int depositCount = 5;
            for (int i = 0; i < depositCount; i++) {
                Nordea.deposit("Bob", new Money(depositAmount, SEK));
                SweBank.deposit("Ulrika", new Money(depositAmount, SEK));
                DanskeBank.deposit("Gertrud", new Money(depositAmount, DKK));

            }
            assertEquals(initialBob + depositAmount * depositCount, Nordea.getBalance("Bob").intValue());
            assertEquals(initialUlrika + depositAmount * depositCount, SweBank.getBalance("Ulrika").intValue());
            assertEquals(initialGertrud + depositAmount * depositCount, DanskeBank.getBalance("Gertrud").intValue());
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
    }

    //failed: withdrawing from an existent account throwed an exception
    /*assert that the method throws an AccountDoesNotExistException*/
    @Test(expected = AccountDoesNotExistException.class)
    public void testWithdraw() throws AccountDoesNotExistException {
        try {
            int initialBob = Nordea.getBalance("Bob");
            int initialUlrika = SweBank.getBalance("Ulrika");
            int initialGertrud = DanskeBank.getBalance("Gertrud");

            int withdrawnAmount = 1000;
            int withdrawCount = 5;
            for (int i = 0; i < withdrawCount; i++) {
                Nordea.withdraw("Bob", new Money(withdrawnAmount, SEK));
                SweBank.withdraw("Ulrika", new Money(withdrawnAmount, SEK));
                DanskeBank.withdraw("Gertrud", new Money(withdrawnAmount, DKK));
            }
            assertEquals(initialBob - withdrawnAmount * withdrawCount, Nordea.getBalance("Bob").intValue());
            assertEquals(initialUlrika - withdrawnAmount * withdrawCount, SweBank.getBalance("Ulrika").intValue());
            assertEquals(initialGertrud - withdrawnAmount * withdrawCount, DanskeBank.getBalance("Gertrud").intValue());
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
             * checking if the returned balance is correct
             * */
            String msg = "Should equal 0";
            assertEquals(msg, 0, SweBank.getBalance("Bob").intValue());
            assertEquals(msg, 0, Nordea.getBalance("Bob").intValue());
            assertEquals(msg, 0, DanskeBank.getBalance("Gertrud").intValue());
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if getting balance of existent accounts throws an exception
             * */
            fail("Should not throw an exception");
        }
        /*
         * checking if getting balance of non-existent account throws an exception
         * */
        SweBank.getBalance("NonExistentAcc");
    }

    //failed because of null-pointer exception
    @Test(expected = AccountDoesNotExistException.class)
    public void testTransfer() throws AccountDoesNotExistException {
        try {
            int initialBobNordea = Nordea.getBalance("Bob");
            int initialBobSwe = SweBank.getBalance("Bob");
            int initialUlrika = SweBank.getBalance("Ulrika");

            int withdrawnAmount = 1000;
            int withdrawCount = 5;

            for (int i = 0; i < withdrawCount; i++) {
                Nordea.transfer("Bob", SweBank, "Ulrika", new Money(1000, SEK));
                SweBank.transfer("Bob", "Ulrika", new Money(1000, SEK));
            }
            assertEquals(initialBobNordea - withdrawnAmount * withdrawCount, Nordea.getBalance("Bob").intValue());
            assertEquals(initialBobSwe - withdrawnAmount * withdrawCount, SweBank.getBalance("Bob").intValue());
            assertEquals(initialUlrika + (withdrawnAmount * withdrawCount) * 2, SweBank.getBalance("Ulrika").intValue());
        } catch (AccountDoesNotExistException e) {
            /*
             * checking if performing transfer between existent accounts throws an exception
             * */
            e.printStackTrace();
            fail("Should not throw an exception");
        }
        /*
         * checking if performing transfer from non-existing account throws an exception
         * */
        SweBank.transfer("NonExistentAcc", "Bob", new Money(1000, SEK));
        DanskeBank.transfer("NonExistentAcc", SweBank, "Ulrika", new Money(1000, DKK));
    }

    //failed because of null-pointer exception - the tested method didn't throw appropriate exception when the account didn't exist
    /*asserting that AccountDoesNotExistException is thrown*/
    @Test(expected = AccountDoesNotExistException.class)
    public void testTimedPayment() throws AccountDoesNotExistException {
        int interval = 5;
        int next = 5;
        int tickCount = 20;
        Money withdrawn = new Money(1000, SEK);
        try {
            /*
             * getting initial amount of money deposited on the account
             * */
            String fromAcc = "Bob";
            int expectedAmount = SweBank.getBalance(fromAcc);
            /*
             * adding a timed payment and decreasing the initial(expected) amount by the withdrawn sum
             * every time the timed payment should be performed
             * */
            SweBank.addTimedPayment(fromAcc, "BobInsurance", interval, next, withdrawn, DanskeBank, "Gertrud");
            for (int i = 0; i < tickCount; i++) {
                SweBank.tick();
                if (next == 0) {
                    next = interval;
                    expectedAmount -= withdrawn.getAmount();
                } else {
                    next--;
                }
            }
            /*checking if the manually decreased amount equals the account balance*/
            assertEquals(expectedAmount, SweBank.getBalance(fromAcc).intValue());
        } catch (AccountDoesNotExistException e) {
            fail("Should not throw an exception");
        }
        /*
         * adding a timed payment from a non-existent account
         * */
        DanskeBank.addTimedPayment("NonExistentAcc", "Insurance", 1, 0, withdrawn, Nordea, "Bob");
        DanskeBank.tick();
    }
}
