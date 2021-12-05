package io.github.joblo2213.adventofcode.y2021.day05;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day05B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<Line> lines = input.map(Line::parse).toList();
        Map<Pos, Long> points = lines.parallelStream()
                .flatMap(Line::positions)
                //Count how often each position occurs
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return points.values().stream().filter(i -> i >= 2).count();
    }
}
