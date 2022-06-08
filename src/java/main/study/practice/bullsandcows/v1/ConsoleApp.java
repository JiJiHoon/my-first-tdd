package study.practice.bullsandcows.v1;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Game game = new Game(new RandomNumberGeneratorImpl());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(game.flushOutputMessage());
            game.processInput(scanner.nextLine());
            if (game.isFinished()) {
                System.out.print(game.flushOutputMessage());
                break;
            }
        }
    }
}
