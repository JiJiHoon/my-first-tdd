package study.practice.bullsandcows.v2;

import java.util.Arrays;

public class BullsAndCows {
    private static final String NEW_LINE = System.lineSeparator();

    private final RandomIntegerGenerator randomIntegerGenerator;

    private String message;

    private int[] answer;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        message = "1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ";
    }

    public String getMessage() {
        String result = message;
        message = "";
        return result;
    }

    public void selectMenu(String input) {
        if (input.equals("1")) {
            message = "Game start! Guess the number!" + NEW_LINE;
            answer = randomIntegerGenerator.getRandomArray();
        } else {
            message = "Quit! Bye!" + NEW_LINE;
        }
    }

    public void guessNumbers(String input) {
        int[] numbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        int strikeCount = 0;

        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == numbers[i]) {
                ++strikeCount;
            }
        }

        message += strikeCount + " strike" + (strikeCount > 1 ? "s" : "") + "!";

        if (strikeCount == 3) {
            message += " You win!" + NEW_LINE;
        } else {
            message += "Try again!" + NEW_LINE;
        }
    }
}
