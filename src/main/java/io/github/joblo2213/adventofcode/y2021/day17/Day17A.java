package io.github.joblo2213.adventofcode.y2021.day17;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Day17A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        TargetArea T = TargetArea.parse(input.toString());
        return IntStream.rangeClosed(0, Math.abs(T.ymin()))
                .mapToObj(vy -> IntStream.rangeClosed(0, T.xmax()).mapToObj(vx -> new Probe(vx, vy)))
                .flatMap(Function.identity())
                .filter(T::hits)
                .flatMap(Probe::path)
                .mapToInt(Probe.Vector::y)
                .max().orElseThrow(() -> new RuntimeException("probe never hits"));
    }
}
