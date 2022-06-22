package study.practice.bullsandcows.v2;

import java.util.Arrays;

public class BullsAndCows {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String MENU_MESSAGE = "1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ";
    private static final String RETRY_MESSAGE = " Try again!";
    private final RandomIntegerGenerator randomIntegerGenerator;
    private final MessageOutput messageOutput = new MessageOutput();
    private int[] answer;
    private int outCount;
    private boolean isGuessNumber;
    private boolean isComplete;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        messageOutput.print(MENU_MESSAGE);
        outCount = 0;
        isGuessNumber = false;
        isComplete = false;
    }

    public String getMessage() {
        return messageOutput.flushMessage();
    }

    public void processInput(String input) {
        if (!isGuessNumber) {
            if (input.equals("1")) {
                messageOutput.println("Game start! Guess the number!");
                answer = randomIntegerGenerator.getRandomArray();
                isGuessNumber = true;
                outCount = 0;
                return;
            }
            messageOutput.println("Quit! Bye!");
            isComplete = true;
            return;
        }

        int[] guess = convertInputToGuessNumbers(input);

        int strikeCount = getStrikeCount(guess);
        int ballCount = getBallCount(guess);

        String strikeMessage = getStrikeMessage(strikeCount);
        String ballMessage = getBallMessage(ballCount);

        if (strikeCount > 0 && ballCount > 0) {
            messageOutput.println(strikeMessage + " " + ballMessage + RETRY_MESSAGE);
        } else if (strikeCount > 0) {
            if (strikeCount == 3) {
                messageOutput.println(strikeMessage + " You win!");
                messageOutput.print(MENU_MESSAGE);
                isGuessNumber = false;
            } else {
                messageOutput.println(strikeMessage + RETRY_MESSAGE);
            }
        } else if (ballCount > 0) {
            messageOutput.println(ballMessage + RETRY_MESSAGE);
        } else {
            ++outCount;
            messageOutput.print(outCount + " out!");
            if (outCount == 3) {
                messageOutput.println(" You lose!");
                messageOutput.print(MENU_MESSAGE);
                isGuessNumber = false;
            } else {
                messageOutput.println(RETRY_MESSAGE);
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
}