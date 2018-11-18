package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100, EUR1_5, SEK2_56;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
        EUR1_5 = new Money(150, EUR);
        SEK2_56 = new Money(256, SEK);
    }

    @Test
    public void testGetAmount() {
        /*
         * consequently getting amounts of money and checking if they correspond to the passed parameters
         * */
        assertEquals(10000, SEK100.getAmount().intValue());
        assertEquals(20000, SEK200.getAmount().intValue());
        assertEquals(1000, EUR10.getAmount().intValue());
        assertEquals(2000, EUR20.getAmount().intValue());
        assertEquals(0, SEK0.getAmount().intValue());
        assertEquals(0, EUR0.getAmount().intValue());
        assertEquals(-10000, SEKn100.getAmount().intValue());
    }

    @Test
    public void testGetCurrency() {
        /*
         * comparing if returned currencies as the same as the ones passed as parameters
         * */
        assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
        assertEquals(SEK, SEKn100.getCurrency());
        assertNotEquals(DKK, EUR0.getCurrency());
    }

    @Test
    public void testToString() {
        /*
         * checking if toString() correctly works with positive, negative values and fractions
         * */
        assertEquals("100 SEK", SEK100.toString());
        assertEquals("-100 SEK", SEKn100.toString());
        assertEquals("0 EUR", EUR0.toString());
        assertEquals("1.5 EUR", EUR1_5.toString());
        assertEquals("2.56 SEK", SEK2_56.toString());
    }

    @Test
    public void testGlobalValue() {
        assertEquals(1500, SEK100.universalValue().intValue());
        assertEquals(0, EUR0.universalValue().intValue());
        assertEquals(3000, EUR20.universalValue().intValue());
        assertEquals(-1500, SEKn100.universalValue().intValue());
        assertNotEquals(200, SEK200.universalValue().intValue());

    }

    @Test
    public void testEqualsMoney() {
        /*
         * checking if equivalent amounts of money are compared correctly
         * */
        assertEquals(SEK100, EUR10);
        assertEquals(SEK200, EUR20);
        assertNotEquals(SEK200, EUR10);
    }

    @Test
    public void testAdd() {
        /*
         * adding money of different currencies and checking if it corresponds
         * to equivalent amount in one of those currencies
         * */
        assertEquals(EUR20, SEK100.add(EUR10));
        assertEquals(EUR20, SEK200.add(EUR0));
        assertNotEquals(EUR0, SEKn100.add(EUR20));
    }

    @Test
    public void testSub() {
        /*
         * subtracting money of different currencies and checking if the result corresponds
         * to equivalent amount in one of those currencies
         * */
        assertEquals(EUR10, SEK100.sub(EUR0));
        assertEquals(EUR0, SEK100.sub(EUR10));
        assertNotEquals(EUR10, SEK100.sub(EUR20));
    }

    @Test
    public void testIsZero() {
        /*
         * checking if isZero() returns correct results for zero and non-zero values
         * */
        assertTrue(EUR0.isZero());
        assertFalse(SEK200.isZero());
    }

    @Test
    public void testNegate() {
        /*
         * negating value of money and checking if it corresponds
         * to equivalent amount in the same or different currency
         * */
        assertEquals(EUR0, EUR0.negate());
        assertEquals(EUR10, SEKn100.negate());
        assertNotEquals(EUR0, EUR10.negate());
    }

    @Test
    public void testCompareTo() {
        assertEquals(1, EUR0.compareTo(SEKn100));
        assertEquals(0, EUR10.compareTo(SEK100));
        assertEquals(-1, SEKn100.compareTo(EUR20));
        assertNotEquals(1, EUR20.compareTo(SEK200));
    }
}
