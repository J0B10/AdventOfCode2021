package io.github.joblo2213.adventofcode.y2020.day19;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;

public class Day19B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> split = input.split("\n\n");
        PuzzleInput rules = split.get(0);
        PuzzleInput strings = split.get(1);
        Alphabet alphabet = new Alphabet();
        rules.forEach(alphabet::addRule);
        alphabet.setRule(8, new MultiListRule(
                new ListRule(alphabet, 42),
                new ListRule(alphabet, 42, 8))
        );
        alphabet.setRule(11, new MultiListRule(
                new ListRule(alphabet, 42, 31),
                new ListRule(alphabet, 42, 11, 31))
        );
        long count = strings.lines().filter(alphabet::matches).count();
        return count;
    }
}
