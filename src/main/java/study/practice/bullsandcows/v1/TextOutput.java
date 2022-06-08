package study.practice.bullsandcows.v1;

public class TextOutput {
    private final StringBuilder sb;

    public TextOutput() {
        sb = new StringBuilder();
    }

    public void print(String message) {
        sb.append(message);
    }

    public void printLine(String message) {
        sb.append(message);
        sb.append(System.lineSeparator());
    }

    public String flush() {
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
