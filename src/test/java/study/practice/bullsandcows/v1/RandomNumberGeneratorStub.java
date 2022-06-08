package study.practice.bullsandcows.v1;

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
