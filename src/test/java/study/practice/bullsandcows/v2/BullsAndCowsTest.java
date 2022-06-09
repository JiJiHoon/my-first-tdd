package study.practice.bullsandcows.v2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BullsAndCowsTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void startMessage() {
        // given
        BullsAndCows bullsAndCows = new BullsAndCows();

        // when
        String actual = bullsAndCows.getMessage();

        // then
        assertThat(actual).isEqualTo("1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ");
    }
}
