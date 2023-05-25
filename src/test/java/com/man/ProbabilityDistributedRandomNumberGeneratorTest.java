package com.man;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightDistributedRandomNumberGeneratorTest {

    /**
     * The test demonstrates the usage by generating 100 random numbers and counting the occurrences of each number.
     */
    @Test
    void nextNum() {
        //given
        int[] randomNums = {-1, 0, 1, 2, 3};
        float[] weightedProbabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        int testCount = 100;

        WeightDistributedRandomNumberGenerator randomGenerator = new WeightDistributedRandomNumberGenerator(randomNums, weightedProbabilities);
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
            double frequencyGap = Math.abs(achievedFrequency - weightedProbabilities[i]);
            assertTrue(frequencyGap < 0.1);
        }

        String testSummary = IntStream.range(0, randomNums.length)
                .mapToObj(i -> getTestSummary(randomNums[i], frequencies[i], weightedProbabilities[i]))
                .collect(Collectors.joining());
        System.out.println(testSummary);
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