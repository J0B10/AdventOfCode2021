package io.github.joblo2213.adventofcode.y2020.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day06A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> groups = input.split("\n\\s*\n");
        return groups.stream().mapToLong(this::countAnsweredByAnyone).sum();
    }

    public long countAnsweredByAnyone(PuzzleInput group) {
        return group.lines().flatMapToInt(String::chars).distinct().count();
    }
}
