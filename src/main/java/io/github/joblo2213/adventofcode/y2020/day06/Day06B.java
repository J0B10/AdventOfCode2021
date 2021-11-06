package io.github.joblo2213.adventofcode.y2020.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;

public class Day06B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> groups = input.split("\n\\s*\n");
        return groups.stream().mapToLong(this::countAnsweredByEveryone).sum();
    }

    public long countAnsweredByEveryone(PuzzleInput group) {
        List<String> groupAnswers = group.getLines();
        return switch (groupAnswers.size()) {
            case 0 -> 0;
            case 1 -> groupAnswers.get(0).length();
            default -> groupAnswers.get(0).chars().mapToObj(c -> String.valueOf((char) c))  //stream of Characters for first group member
                    .filter(ch -> groupAnswers.stream().allMatch(s -> s.contains(ch)))
                    .count();
        };
    }
}
