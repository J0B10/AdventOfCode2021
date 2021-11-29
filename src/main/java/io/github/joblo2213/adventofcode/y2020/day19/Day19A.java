package io.github.joblo2213.adventofcode.y2020.day19;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;

public class Day19A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> split = input.split("\n\n");
        PuzzleInput rules = split.get(0);
        PuzzleInput strings = split.get(1);
        Alphabet alphabet = new Alphabet();
        rules.forEach(alphabet::addRule);
        long count = strings.lines().filter(alphabet::matches).count();
        return count;
    }
}
