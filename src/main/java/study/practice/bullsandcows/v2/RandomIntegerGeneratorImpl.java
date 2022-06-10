package study.practice.bullsandcows.v2;

import java.util.Random;

public class RandomIntegerGeneratorImpl implements RandomIntegerGenerator {
    private final Random random;

    public RandomIntegerGeneratorImpl() {
        this.random = new Random();
    }

    @Override
    public int[] getRandomArray() {
        int[] result = new int[3];

        for (int i = 0; i < 3; i++) {
            result[i] = random.nextInt(10);
        }

        return result;
    }
}
