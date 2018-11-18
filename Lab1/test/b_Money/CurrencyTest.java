package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency SEK, DKK, NOK, EUR;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        NOK = new Currency("NOK", 2.0);
    }

    @Test
    public void testGetName() {
        /*
         * comparing gotten names with the actual ones
         * */
        assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("EUR", EUR.getName());
    }

    @Test
    public void testGetRate() {
        /*
         * comparing assigned rates to the returned ones
         * */
        assertEquals(0.15, SEK.getRate(), 0);
        assertEquals(0.20, DKK.getRate(), 0);
        assertEquals(1.5, EUR.getRate(), 0);
    }

    @Test
    public void testSetRate() {
        /*changing the rate and checking if it was assigned correctly*/
        SEK.setRate(100.0);
        DKK.setRate(200.0);
        EUR.setRate(300.0);
        assertEquals(100, SEK.getRate(), 0);
        assertEquals(200, DKK.getRate(), 0);
        assertEquals(300, EUR.getRate(), 0);
    }

    @Test
    public void testGlobalValue() {
        /*
         * converting amount of money in a given currency to universal value
         * */
        assertEquals(15, SEK.universalValue(100).intValue());
        assertEquals(20, DKK.universalValue(100).intValue());
        assertEquals(150, EUR.universalValue(100).intValue());
        assertEquals(200, NOK.universalValue(100).intValue());
    }

    @Test
    public void testValueInThisCurrency() {
        /*
         * converting amount of money to another currency and checking
         * correctness of the conversion
         * */
        assertEquals(1000, SEK.valueInThisCurrency(100, EUR).intValue());
        assertEquals(2000, DKK.valueInThisCurrency(200, NOK).intValue());
    }

}
