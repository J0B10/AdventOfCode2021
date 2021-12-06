package io.github.joblo2213.adventofcode.y2021.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.stream.IntStream;

public class Day06B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        return input.withDelimiter(",").parallel().ints().mapToObj(IntStream::of).flatMapToInt(population -> {
            for (int i = 0; i < 256; i++) {
                population = population
                        .map(fish -> fish - 1)
                        .flatMap(fish -> fish == -1 ? IntStream.of(6, 8) : IntStream.of(fish));
            }
            return population;
        }).count();
    }
}
