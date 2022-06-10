package study.practice.bullsandcows.v2;

public class RandomIntegerGeneratorStub implements RandomIntegerGenerator {
    private int[] nums;

    public RandomIntegerGeneratorStub(int[] nums) {
        this.nums = nums;
    }

    @Override
    public int[] getRandomArray() {
        return nums;
    }
}
