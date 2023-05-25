package com.man;


import java.util.Random;

/**
 * In this Java implementation of random generator with weighted probabilities
 * takes in an array of numbers and an array of probabilities in its constructor.
 * The nextNum method generates a random number based on the given probabilities.
 */
public final class ProbabilityDistributedRandomNumberGenerator {

    // Values that may be returned by nextNum()
    private final int[] randomNums;
    // Probability of the occurrence of randomNums
    private final float[] probabilities;
    private final Random random;

    public ProbabilityDistributedRandomNumberGenerator(int[] randomNums, float[] probabilities) {
        this.randomNums = randomNums;
        this.probabilities = probabilities;
        this.random = new Random();
    }

    /**
     * Returns one of the randomNums. When this method is called multiple times over a long period,
     * it should return the numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        float nextFloat = random.nextFloat();
        float cumulativeProbability = 0.0f;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (nextFloat < cumulativeProbability) {
                return randomNums[i];
            }
        }
        return randomNums[randomNums.length - 1];
    }
}


