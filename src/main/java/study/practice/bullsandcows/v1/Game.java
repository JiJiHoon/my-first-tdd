package study.practice.bullsandcows.v1;

public class Game {
    private static final String NEW_LINE = System.lineSeparator();
    private final RandomNumberGenerator randomNumberGenerator;
    private final TextOutput textOutput;
    private int[] numbers;
    private Processor processor;
    private int outCount;
    private boolean isFinished;

    public Game(RandomNumberGenerator randomNumberGenerator) {
        textOutput = new TextOutput();
        this.randomNumberGenerator = randomNumberGenerator;
        this.processor = getMenuProcessor();
        outCount = 0;
        isFinished = false;
    }

    public void processInput(String input) {
        processor = processor.run(input);
    }

    public String flushOutputMessage() {
        return textOutput.flush();
    }

    public boolean isFinished() {
        return isFinished;
    }

    private Processor getMenuProcessor() {
        textOutput.print("0: Play game" + NEW_LINE + "1: Quit" + NEW_LINE + "Select Mode: ");
        outCount = 0;
        return (input) -> {
            if (input.equals("0")) {
                textOutput.printLine("Guess numbers!");
                numbers = randomNumberGenerator.generateArray(3);
                return getGameProcessor();
            }
            textOutput.printLine("Quit! Bye!");
            isFinished = true;
            return null;
        };
    }

    private Processor getGameProcessor() {
        return (input) -> {
            String[] strings = input.split(",");

            int strikeCount = 0;
            int ballCount = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (Integer.parseInt(strings[i]) == numbers[i]) {
                    ++strikeCount;
                    continue;
                }

                for (int number : numbers) {
                    if (Integer.parseInt(strings[i]) == number) {
                        ++ballCount;
                        break;
                    }
                }
            }

            if (strikeCount == 0 && ballCount == 0) {
                ++outCount;
                if (outCount == 3) {
                    textOutput.printLine("3 Out! You lose!");
                    return getMenuProcessor();
                } else {
                    textOutput.printLine(outCount + " Out! Try again.");
                }
            } else if (strikeCount == 3) {
                textOutput.printLine("3 Strikes! You win!");
                return getMenuProcessor();
            } else {
                String ballsAndStrikes = "";
                if (ballCount > 0) {
                    ballsAndStrikes += (ballCount + " " + (ballCount == 1 ? "Ball" : "Balls") + "! ");
                }
                if (strikeCount > 0) {
                    ballsAndStrikes += (strikeCount + " " + (strikeCount == 1 ? "Strike" : "Strikes") + "! ");
                }
                textOutput.print(ballsAndStrikes);
                textOutput.printLine("Try again.");
            }

            return getGameProcessor();
        };
    }

    private interface Processor {
        Processor run(String input);
    }
}
