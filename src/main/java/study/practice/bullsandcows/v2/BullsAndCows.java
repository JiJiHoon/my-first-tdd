package study.practice.bullsandcows.v2;

public class BullsAndCows {
    private static final String NEW_LINE = System.lineSeparator();

    private final RandomIntegerGenerator randomIntegerGenerator;

    private String message;

    public BullsAndCows(RandomIntegerGenerator randomIntegerGenerator) {
        this.randomIntegerGenerator = randomIntegerGenerator;
        message = "1: single play mode" + NEW_LINE + "2: quit" + NEW_LINE + "Select Mode: ";
    }

    public String getMessage() {
        return message;
    }

    public void selectMenu(String input) {
        if (input.equals("1")) {
            message = "Game start! Guess the number!" + NEW_LINE;
        } else {
            message = "Quit! Bye!" + NEW_LINE;
        }
    }
}
