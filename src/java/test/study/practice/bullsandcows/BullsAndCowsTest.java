package study.practice.bullsandcows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BullsAndCowsTest {
    @Test
    void testStartMessage() {
        // given
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();

        Game game = new Game(input, output);

        // when
        game.run();

        // then
        assertEquals("Game started!", output.toString());
    }
}
