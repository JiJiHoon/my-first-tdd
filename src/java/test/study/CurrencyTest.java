package study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {
    @Test
    void testMultiplication() {
        // given
        Dollar five = new Dollar(5);

        // when
        Dollar product = five.times(2);

        // then
        assertEquals(10, product.amount);

        // when
        product = five.times(3);

        // then
        assertEquals(15, product.amount);
    }
}
