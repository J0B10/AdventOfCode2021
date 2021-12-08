package io.github.joblo2213.adventofcode.y2021.day08;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Arrays;

public class Day08A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        return input.lines()
                .filter(s -> !s.trim().endsWith("|"))
                .map(s -> s.replaceFirst(".*\\| ", ""))
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> switch (s.length()) {
                    case 2, 3, 4, 7 -> true;
                    default -> false;
                })
                .count();
    }
}
