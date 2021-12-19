package io.github.joblo2213.adventofcode.y2021.day18;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;

public class Day18A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<SnailfishNumber> snailfish = input.mapToList(SnailfishNumber::parse);
        SnailfishNumber s = snailfish.remove(0);
        for (SnailfishNumber sn : snailfish) {
            s.add(sn);
        }
        return s.magnitude();
    }
}
