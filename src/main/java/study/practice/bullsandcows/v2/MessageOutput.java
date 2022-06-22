package study.practice.bullsandcows.v2;

public class MessageOutput {
    private static final String NEW_LINE = System.lineSeparator();

    private final StringBuilder stringBuilder;

    public MessageOutput() {
        this.stringBuilder = new StringBuilder();
    }

    public String flushMessage() {
        String result = stringBuilder.toString();
        stringBuilder.setLength(0);
        return result;
    }

    public void print(String message) {
        stringBuilder.append(message);
    }

    public void println(String message) {
        print(message);
        stringBuilder.append(NEW_LINE);
    }
}
