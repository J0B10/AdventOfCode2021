package io.github.joblo2213.adventofcode.y2021.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Arrays;

public class Day06B extends Task {

    private final int cycles;

    public Day06B(int cycles) {
        this.cycles = cycles;
    }

    public Day06B() {
        this(256);
    }

    @Override
    public long run(PuzzleInput input) throws Exception {
        final long[] population = new long[9];
        for (int i : input.withDelimiter(",").getInts()) population[i]++;
        for (int i = 0; i < cycles; i++) updatePopulation(population);
        return Arrays.stream(population).sum();
    }

    @SuppressWarnings("SuspiciousSystemArraycopy")
    static void updatePopulation(long[] population) {
        long reproducing = population[0];
        System.arraycopy(population, 1, population, 0, 8);
        population[6] += reproducing;
        population[8] = reproducing;
    }
}
