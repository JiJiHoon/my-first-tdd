package study.practice.bullsandcows.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BullsAndCowsTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void testStartMessage() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{0, 0, 0}));

        // when
        String output = sut.flushOutputMessage();

        // then
        assertEquals("0: Play game" + NEW_LINE + "1: Quit" + NEW_LINE + "Select Mode: ", output);
    }

    @Test
    void testModeSelection() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{0, 0, 0}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        String output = sut.flushOutputMessage();

        // then
        assertEquals("Guess numbers!" + NEW_LINE, output);
    }

    @Test
    void testRandomNumbers() {
        // given
        RandomNumberGeneratorImpl sut = new RandomNumberGeneratorImpl();

        // when
        int[] numbers = sut.generateArray(3);

        // then
        assertEquals(3, numbers.length);
    }

    @ParameterizedTest()
    @CsvSource({
            "0, 0, 0, '0,0,0', 3 Strikes",
            "1, 2, 3, '1,2,3', 3 Strikes",
            "1, 2, 3, '0,2,3', 2 Strikes",
            "1, 2, 3, '1,0,0', 1 Strike",
    })
    void testStrikes(int num1, int num2, int num3, String input, String expected) {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{num1, num2, num3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput(input);
        String output = sut.flushOutputMessage();

        // then
        if (expected.equals("3 Strikes")) {
            assertEquals("3 Strikes! You win!" + NEW_LINE + "0: Play game" + NEW_LINE + "1: Quit" + NEW_LINE + "Select Mode: ", output);
        } else {
            assertEquals(expected + "! Try again." + NEW_LINE, output);
        }
    }

    @ParameterizedTest()
    @CsvSource({
            "0, 0, 0, '1,1,1'",
            "1, 2, 3, '4,5,6'"
    })
    void testOut(int num1, int num2, int num3, String input) {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{num1, num2, num3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput(input);
        String output = sut.flushOutputMessage();

        // then
        assertEquals("1 Out! Try again." + NEW_LINE, output);
    }

    @ParameterizedTest()
    @CsvSource({
            "1, 2, 3, '3,4,5', 1 Ball",
            "1, 2, 3, '3,1,5', 2 Balls",
            "1, 2, 3, '3,1,2', 3 Balls",
    })
    void testBall(int num1, int num2, int num3, String input, String expected) {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{num1, num2, num3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput(input);
        String output = sut.flushOutputMessage();

        // then
        assertEquals(expected + "! Try again." + NEW_LINE, output);
    }


    @ParameterizedTest()
    @CsvSource({
            "1, 2, 3, '3,2,5', 1 Ball! 1 Strike",
            "1, 2, 3, '2,2,3', 1 Ball! 2 Strikes",
            "1, 2, 3, '2,1,3', 2 Balls! 1 Strike",
    })
    void testBallAndStrike(int num1, int num2, int num3, String input, String expected) {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{num1, num2, num3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput(input);
        String output = sut.flushOutputMessage();

        // then
        assertEquals(expected + "! Try again." + NEW_LINE, output);
    }

    @Test
    void test3Out() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{1, 2, 3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput("0,0,0");
        sut.flushOutputMessage();
        sut.processInput("0,0,0");
        sut.flushOutputMessage();
        sut.processInput("0,0,0");
        String output = sut.flushOutputMessage();

        // then
        assertEquals("3 Out! You lose!" + NEW_LINE + "0: Play game" + NEW_LINE + "1: Quit" + NEW_LINE + "Select Mode: ", output);
    }

    @Test
    void testQuit() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{1, 2, 3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("1");
        String output = sut.flushOutputMessage();

        // then
        assertEquals("Quit! Bye!" + NEW_LINE, output);
        assertTrue(sut.isFinished());
    }

    @Test
    void testIsFinished() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{1, 2, 3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");

        // then
        assertFalse(sut.isFinished());
    }

    @Test
    void test3OutAndRetry() {
        // given
        Game sut = new Game(new RandomNumberGeneratorStub(new int[]{1, 2, 3}));

        // when
        sut.flushOutputMessage();
        sut.processInput("0");
        sut.flushOutputMessage();

        sut.processInput("0,0,0");
        sut.flushOutputMessage();
        sut.processInput("0,0,0");
        sut.flushOutputMessage();
        sut.processInput("0,0,0");
        String output = sut.flushOutputMessage();

        // then
        assertEquals("3 Out! You lose!" + NEW_LINE + "0: Play game" + NEW_LINE + "1: Quit" + NEW_LINE + "Select Mode: ", output);

        sut.processInput("0");
        sut.flushOutputMessage();
        sut.processInput("0,0,0");
        output = sut.flushOutputMessage();
        assertEquals("1 Out! Try again." + NEW_LINE, output);
    }
}