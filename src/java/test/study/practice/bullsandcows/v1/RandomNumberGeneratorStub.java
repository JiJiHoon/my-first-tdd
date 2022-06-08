package study.practice.bullsandcows.v1;

import study.practice.bullsandcows.v1.RandomNumberGenerator;

public class RandomNumberGeneratorStub implements RandomNumberGenerator {
    private int[] result;

    public RandomNumberGeneratorStub(int[] stubArray) {
        result = stubArray;
    }

    @Override
    public int[] generateArray(int size) {
        return result;
    }
}
