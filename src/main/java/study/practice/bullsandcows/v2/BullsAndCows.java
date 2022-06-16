package study.practice.bullsandcows.v2;

import java.util.Arrays;

public class BullsAndCows {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String MENU_MESSAGE = "1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ";
    private final RandomIntegerGenerator randomIntegerGenerator;

    private String message;

    private int[] answer;
    private int outCount;
    private boolean isGuessNumber;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        message = MENU_MESSAGE;
        outCount = 0;
        isGuessNumber = false;
    }

    public String getMessage() {
        String result = message;
        message = "";
        return result;
    }

    public void processInput(String input) {
        if (isGuessNumber) {
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

            if (strikeCount == 0 && ballCount == 0) {
                ++outCount;
                message += outCount + " out!";
                if (outCount == 3) {
                    message += " You lose!" + NEW_LINE;
                    message += MENU_MESSAGE;
                    isGuessNumber = false;
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
                message += MENU_MESSAGE;
                isGuessNumber = false;
            } else {
                message += " Try again!" + NEW_LINE;
            }
        } else {
            if (input.equals("1")) {
                message = "Game start! Guess the number!" + NEW_LINE;
                answer = randomIntegerGenerator.getRandomArray();
                isGuessNumber = true;
            } else {
                message = "Quit! Bye!" + NEW_LINE;
            }
        }
    }
}
