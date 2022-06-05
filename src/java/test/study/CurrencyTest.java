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
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    @Test
    void testReduceSum() {
        // given
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();

        // when
        Money result = bank.reduce(sum, "USD");

        // then
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        // given
        Bank bank = new Bank();

        // when
        Money result = bank.reduce(Money.dollar(1), "USD");

        // then
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testReduceMoneyDiff() {
        // given
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        // when
        Money result = bank.reduce(Money.franc(2), "USD");

        // then
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    @Test
    void testMixedAddition() {
        // given
        Expression dollar = Money.dollar(5);
        Expression franc = Money.franc(10);

        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        // when
        Money usd = bank.reduce(dollar.plus(franc), "USD");

        // then
        assertEquals(Money.dollar(10), usd);
    }

    @Test
    void testSumPlusMoney() {
        // given
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);

        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        // when
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");

        // then
        assertEquals(Money.dollar(15), result);
    }

    @Test
    void testSumTimes() {
        // given
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);

        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        // when
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");

        // then
        assertEquals(Money.dollar(20), result);
    }
}
