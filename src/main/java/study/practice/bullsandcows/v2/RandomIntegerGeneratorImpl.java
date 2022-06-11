package study.practice.bullsandcows.v2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomIntegerGeneratorImpl implements RandomIntegerGenerator {
    private final Random random;

    public RandomIntegerGeneratorImpl() {
        this.random = new Random();
    }

    @Override
    public int[] getRandomArray() {
        Set<Integer> result = new HashSet<>();

        while (result.size() < 3) {
            result.add(random.nextInt(10));
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
