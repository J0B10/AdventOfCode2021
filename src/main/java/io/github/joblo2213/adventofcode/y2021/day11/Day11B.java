package io.github.joblo2213.adventofcode.y2021.day11;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day11B extends Task {

    record Pos(int x, int y) {
    }

    @Override
    public long run(PuzzleInput input) throws Exception {
        int[][] octopuses = input.map(s -> s.chars().map(Character::getNumericValue).toArray()).toArray(int[][]::new);
        int i = 1;
        for (; i < Integer.MAX_VALUE; i++) {
            if (Day11A.step(octopuses) == 10 * 10) break;
        }
        return i;
    }
}
