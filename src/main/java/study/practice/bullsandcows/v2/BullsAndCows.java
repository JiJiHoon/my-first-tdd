package study.practice.bullsandcows.v2;

import java.util.Arrays;

public class BullsAndCows {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String MENU_MESSAGE = "1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ";
    private final RandomIntegerGenerator randomIntegerGenerator;

    private final StringBuilder stringBuilder;

    private int[] answer;
    private int outCount;
    private boolean isGuessNumber;

    private boolean isComplete;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        stringBuilder = new StringBuilder();
        print(MENU_MESSAGE);
        outCount = 0;
        isGuessNumber = false;
        isComplete = false;
    }

    public String getMessage() {
        String result = stringBuilder.toString();
        stringBuilder.setLength(0);
        return result;
    }

    public void processInput(String input) {
        if (!isGuessNumber) {
            if (input.equals("1")) {
                println("Game start! Guess the number!");
                answer = randomIntegerGenerator.getRandomArray();
                isGuessNumber = true;
                return;
            }
            println("Quit! Bye!");
            isComplete = true;
            return;
        }

        int[] guess = convertInputToGuessNumbers(input);

        int strikeCount = getStrikeCount(guess);
        int ballCount = getBallCount(guess);

        if (strikeCount > 0 && ballCount > 0) {
            print(getStrikeMessage(strikeCount));
            print(" ");
            print(getBallMessage(ballCount));
            println(" Try again!");
        } else if (strikeCount > 0) {
            print(getStrikeMessage(strikeCount));
            if (strikeCount == 3) {
                println(" You win!");
                print(MENU_MESSAGE);
                isGuessNumber = false;
            } else {
                println(" Try again!");
            }
        } else if (ballCount > 0) {
            print(getBallMessage(ballCount));
            println(" Try again!");
        } else {
            ++outCount;
            print(outCount + " out!");
            if (outCount == 3) {
                println(" You lose!");
                print(MENU_MESSAGE);
                isGuessNumber = false;
            } else {
                println(" Try again!");
            }
        }
    }

    public boolean isComplete() {
        return isComplete;
    }

    private int getBallCount(int[] guess) {
        int ballCount = 0;

        for (int i = 0; i < answer.length; i++) {
            if (guess[i] == answer[i]) {
                continue;
            }
            for (int j = 0; j < answer.length; j++) {
                if (i != j && guess[i] == answer[j]) {
                    ++ballCount;
                }
            }
        }

        return ballCount;
    }

    private int getStrikeCount(int[] guess) {
        int strikeCount = 0;

        for (int i = 0; i < answer.length; i++) {
            if (guess[i] == answer[i]) {
                ++strikeCount;
            }
        }

        return strikeCount;
    }

    private int[] convertInputToGuessNumbers(String input) {
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private String getBallMessage(int ballCount) {
        return ballCount + " ball" + (ballCount > 1 ? "s" : "") + "!";
    }

    private String getStrikeMessage(int strikeCount) {
        return strikeCount + " strike" + (strikeCount > 1 ? "s" : "") + "!";
    }

    private void print(String message) {
        stringBuilder.append(message);
    }

    private void println(String message) {
        print(message);
        stringBuilder.append(NEW_LINE);
    }
}
