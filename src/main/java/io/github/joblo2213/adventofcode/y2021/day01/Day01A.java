package io.github.joblo2213.adventofcode.y2021.day01;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day01A extends Task {
    @Override
    public long run(PuzzleInput input) {
        int[] depths = input.ints().toArray();
        int increased = 0;
        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > depths[i - 1]) increased++;
        }
        return increased;
    }
}
