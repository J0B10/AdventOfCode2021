package io.github.joblo2213.adventofcode.y2021.day05;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.joblo2213.adventofcode.y2021.day05.Line.Orientation.DIAGONAL;

public class Day05A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        List<Line> lines = input.map(Line::parse).filter(l -> l.getOrientation() != DIAGONAL).toList();
        Map<Pos, Long> points = lines.parallelStream()
                .flatMap(Line::positions)
                //Count how often each position occurs
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return points.values().stream().filter(i -> i >= 2).count();
    }

    /**
     * @deprecated Did work well for the example but did not for the puzzle input.
     * So lets try again from scratch 😭
     */
    @Deprecated
    public long runV1(PuzzleInput input) {
        List<Line> lines = input.map(Line::parse).filter(l -> l.getOrientation() != DIAGONAL).toList();
        Map<Pos, Long> intersections = lines.parallelStream().flatMap(line ->
                lines.stream().filter(l -> !l.equals(line)).flatMap(line::intersections)
        ).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return intersections.size();
    }
}
