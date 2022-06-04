package study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {
    @Test
    void testMultiplication() {
        // given
        Money five = Money.dollar(5);

        // then
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    void testEquality() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(10));

        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.franc(10));

        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testFrancMultiplication() {
        // given
        Franc five = Money.franc(5);

        // then
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testDifferentClassEquality() {
        assertEquals(new Money(10, "CHF"), new Franc(10, "CHF"));
    }
}
