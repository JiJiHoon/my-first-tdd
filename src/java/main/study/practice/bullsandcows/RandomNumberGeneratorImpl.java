package study.practice.bullsandcows;

import java.util.Random;

public class RandomNumberGeneratorImpl implements RandomNumberGenerator{
    @Override
    public int[] generateArray(int size) {
        Random random = new Random();

        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(10);
        }

        return result;
    }
}
