package study.practice.bullsandcows;

public class Game {
    private StringBuilder input;
    private StringBuilder output;

    public Game(StringBuilder input, StringBuilder output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        output.append("Game started!");
    }
}
