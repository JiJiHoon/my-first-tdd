package study.practice.bullsandcows.v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class BullsAndCowsTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void startMessage() {
        // given
        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(new int[]{0, 0, 0}));

        // when
        String actual = sut.getMessage();

        // then
        assertThat(actual).isEqualTo("1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ");
    }

    @Test
    void selectSinglePlayerMode() {
        // given
        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(new int[]{0, 0, 0}));

        // when
        sut.getMessage();
        sut.processInput("1");
        String actual = sut.getMessage();

        // then
        assertThat(actual).isEqualTo("Game start! Guess the number!" + NEW_LINE);
    }

    @Test
    void selectQuit() {
        // given
        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(new int[]{0, 0, 0}));

        // when
        sut.getMessage();
        sut.processInput("2");
        String actualMessage = sut.getMessage();
        boolean actualComplete = sut.isComplete();

        // then
        assertThat(actualMessage).isEqualTo("Quit! Bye!" + NEW_LINE);
        assertThat(actualComplete).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3', '1 2 4', 2",
            "'1 2 3', '1 4 3', 2",
            "'1 2 3', '1 3 2', 1",
            "'1 2 3', '4 4 3', 1",
    })
    void strikes(String answerString, String guess, int expected) {
        // given
        int[] answer = Arrays.stream(answerString.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput(guess);
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains(expected + " strike" + (expected == 1 ? "" : "s"));
        assertThat(actual).contains("Try again!");
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3', '1 2 3'",
            "'4 5 6', '4 5 6'",
    })
    void threeStrikes(String answerString, String guess) {
        // given
        int[] answer = Arrays.stream(answerString.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput(guess);
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains("3 strikes! You win!");
        assertThat(actual).contains("1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ");
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3', '3 1 2', 3",
            "'1 2 3', '2 1 4', 2",
            "'1 2 3', '1 3 2', 2",
            "'1 2 3', '0 0 1', 1",
    })
    void balls(String answerString, String guess, int expected) {
        // given
        int[] answer = Arrays.stream(answerString.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput(guess);
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains(expected + " ball" + (expected == 1 ? "" : "s"));
        assertThat(actual).contains("Try again!");
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3', '4 5 6'"
    })
    void noBalls(String answerString, String guess) {
        // given
        int[] answer = Arrays.stream(answerString.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput(guess);
        String actual = sut.getMessage();

        // then
        assertThat(actual).doesNotContain("ball");
    }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3', '1 2 1', 2, 1",
            "'1 2 3', '1 3 2', 1, 2"
    })
    void strikesAndBalls(String answerString, String guess, int expectedStrikeCount, int expectedBallCount) {
        // given
        int[] answer = Arrays.stream(answerString.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput(guess);
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains(expectedStrikeCount + " strike");
        assertThat(actual).contains(expectedBallCount + " ball");
    }

    @Test
    void out() {
        // given
        int[] answer = new int[]{1, 2, 3};

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput("4 5 6");
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains("1 out");
    }

    @Test
    void threeOuts() {
        // given
        int[] answer = new int[]{1, 2, 3};

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput("4 5 6");
        sut.getMessage();
        sut.processInput("7 8 9");
        sut.getMessage();
        sut.processInput("4 6 7");
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains("3 out! You lose!");
        assertThat(actual).contains("1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ");
    }

    @Test
    void replayAndOut() {
        // given
        int[] answer = new int[]{1, 2, 3};

        BullsAndCows sut = new BullsAndCows(new RandomIntegerGeneratorStub(answer));
        sut.processInput("1");
        sut.getMessage();

        // when
        sut.processInput("4 5 6");
        sut.processInput("7 8 9");
        sut.processInput("4 6 7");
        sut.processInput("1");
        sut.getMessage();
        sut.processInput("4 5 6");
        String actual = sut.getMessage();

        // then
        assertThat(actual).contains("1 out! Try again!");
    }
}
