package io.github.joblo2213.adventofcode.y2021.day03;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.stream.IntStream;

public class Day03A extends Task{
    @Override
    public long run(PuzzleInput input) throws Exception {
        int bits = input.getLines().get(0).length();
        long gamma = IntStream.range(0, bits).map(i -> {
            int common = (int) input.lines().filter(s -> s.charAt(i) == '1').count() * 2 / input.lineCount();
            int shift = bits - i - 1;
            return common << shift;
        }).sum();
        long mask = IntStream.range(0,bits).map(i -> 1 << i).sum();
        long epsilon = ~gamma & mask;
        return gamma * epsilon;
    }
}
