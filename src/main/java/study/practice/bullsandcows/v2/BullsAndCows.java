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
    private boolean isComplete;
    private Processor processor;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        messageOutput.print(MENU_MESSAGE);
        outCount = 0;
        isComplete = false;
        processor = getMenuProcessor();
    }

    public String getMessage() {
        return messageOutput.flushMessage();
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    public boolean isComplete() {
        return isComplete;
    }

    private Processor getMenuProcessor() {
        return input -> {
            if (input.equals("1")) {
                messageOutput.println("Game start! Guess the number!");
                answer = randomIntegerGenerator.getRandomArray();
                outCount = 0;
                return getGuessProcessor();
            }
            messageOutput.println("Quit! Bye!");
            isComplete = true;
            return null;
        };
    }

    private Processor getGuessProcessor() {
        return input -> {
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
                    return getMenuProcessor();
                }
                messageOutput.println(strikeMessage + RETRY_MESSAGE);
            } else if (ballCount > 0) {
                messageOutput.println(ballMessage + RETRY_MESSAGE);
            } else {
                ++outCount;
                messageOutput.print(outCount + " out!");
                if (outCount == 3) {
                    messageOutput.println(" You lose!");
                    messageOutput.print(MENU_MESSAGE);
                    return getMenuProcessor();
                }
                messageOutput.println(RETRY_MESSAGE);
            }
            return getGuessProcessor();
        };
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

    private interface Processor {
        Processor run(String input);
    }
}