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
        int ballCount = 0;

        for (int i = 0; i < answer.length; i++) {
            if (numbers[i] == answer[i]) {
                ++strikeCount;
                continue;
            }
            for (int j = 0; j < answer.length; j++) {
                if (i != j && numbers[i] == answer[j]) {
                    ++ballCount;
                }
            }
        }

        if (strikeCount > 0) {
            message += strikeCount + " strike" + (strikeCount > 1 ? "s" : "") + "!";
        }
        if (ballCount > 0) {
            message += " " + ballCount + " ball" + (ballCount > 1 ? "s" : "") + "!";
        }

        if (strikeCount == 3) {
            message += " You win!" + NEW_LINE;
        } else {
            message += " Try again!" + NEW_LINE;
        }
    }
}
