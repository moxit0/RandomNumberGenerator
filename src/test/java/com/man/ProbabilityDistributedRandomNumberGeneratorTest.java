package com.man;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProbabilityDistributedRandomNumberGeneratorTest {

    /**
     * The test demonstrates the usage by generating 100 random numbers and counting the occurrences of each number.
     */
    @Test
    public void randomGenerator_generatesNumbers_inAcceptableFrequency() {
        //given
        int[] randomNums = {-1, 0, 1, 2, 3};
        float[] weightedProbabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        int testCount = 100;
        ProbabilityDistributedRandomNumberGenerator randomGenerator = new ProbabilityDistributedRandomNumberGenerator(randomNums, weightedProbabilities);
        // Generate and count the results
        int[] frequencies = new int[randomNums.length];

        //when
        for (int i = 0; i < testCount; i++) {
            int randomNumber = randomGenerator.nextNum();
            frequencies[randomNumber + 1]++;
        }

        //then
        for (int i = 0; i < frequencies.length; i++) {
            int occurrences = frequencies[i];
            double achievedFrequency = occurrences * Math.pow(testCount, -1);
            float probability = weightedProbabilities[i];
            double frequencyGap = Math.abs(achievedFrequency - probability);
            System.out.println(getTestSummary(randomNums[i], occurrences, probability));
            assertTrue(frequencyGap < 0.1);
        }
    }

    private String getTestSummary(int number, int frequency, float weight) {
        return new StringBuilder()
                .append(number)
                .append(" occurrences -> ")
                .append(frequency)
                .append(" weight -> (")
                .append(weight)
                .append(")\n").toString();
    }
}