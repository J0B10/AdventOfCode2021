package io.github.joblo2213.adventofcode.y2021.day01;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day01B extends Task {
    @Override
    public long run(PuzzleInput input) {
        int[] depths = input.ints().toArray();
        int increased = 0;
        int previous = 0;
        for (int i = 2; i < depths.length; i++) {
            int sum = depths[i] + depths[i - 1] + depths[i - 2];
            if (i > 2 && sum > previous) increased++;
            previous = sum;
        }
        return increased;
    }
}
