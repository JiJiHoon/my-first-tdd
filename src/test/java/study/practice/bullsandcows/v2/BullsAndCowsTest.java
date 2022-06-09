package study.practice.bullsandcows.v2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BullsAndCowsTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void startMessage() {
        // given
        BullsAndCows sut = new BullsAndCows();

        // when
        String actual = sut.getMessage();

        // then
        assertThat(actual).isEqualTo("1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ");
    }

    @Test
    void selectSinglePlayerMode() {
        // given
        BullsAndCows sut = new BullsAndCows();

        // when
        sut.selectMenu("1");
        String actual = sut.getMessage();

        // then
        assertThat(actual).isEqualTo("Game start! Guess the number!" + NEW_LINE);
    }

    @Test
    void selectQuit() {
        // given
        BullsAndCows sut = new BullsAndCows();

        // when
        sut.selectMenu("2");
        String actual = sut.getMessage();

        // then
        assertThat(actual).isEqualTo("Quit! Bye!" + NEW_LINE);
    }


}
