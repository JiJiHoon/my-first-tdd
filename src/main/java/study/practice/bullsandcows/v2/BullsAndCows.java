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
        print(MENU_MESSAGE);
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
                print(outCount + " out!");
                if (outCount == 3) {
                    println(" You lose!");
                    print(MENU_MESSAGE);
                    isGuessNumber = false;
                }
            }
            if (strikeCount > 0) {
                print(strikeCount + " strike" + (strikeCount > 1 ? "s" : "") + "!");
            }
            if (ballCount > 0) {
                print(" " + ballCount + " ball" + (ballCount > 1 ? "s" : "") + "!");
            }

            if (strikeCount == 3) {
                println(" You win!");
                print(MENU_MESSAGE);
                isGuessNumber = false;
            } else {
                println(" Try again!");
            }
        } else {
            if (input.equals("1")) {
                println("Game start! Guess the number!");
                answer = randomIntegerGenerator.getRandomArray();
                isGuessNumber = true;
            } else {
                println("Quit! Bye!");
            }
        }
    }

    private void print(String message) {
        this.message += message;
    }

    private void println(String message) {
        this.message += message + NEW_LINE;
    }
}
