package io.github.joblo2213.adventofcode.y2021.day09;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.stream.IntStream;

public class Day09A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        int[][] heightmap = input.map(s -> s.chars().map(Character::getNumericValue).toArray()).toArray(int[][]::new);
        int h = input.lineCount(), w = input.getLines().get(0).length();
        return IntStream.range(0, w).mapToLong(y -> IntStream.range(0, h).filter(x -> {
            int val = heightmap[x][y];
            return getNeighbours(x, y, heightmap).allMatch(n -> val < n);
        }).map(x -> heightmap[x][y] + 1).sum()).sum();
    }

    static IntStream getNeighbours(int x, int y, int[][] heightmap) {
        IntStream.Builder b = IntStream.builder();
        if (x > 0) b.accept(heightmap[x - 1][y]);
        if (y > 0) b.accept(heightmap[x][y - 1]);
        if (x + 1 < heightmap.length) b.accept(heightmap[x + 1][y]);
        if (y + 1 < heightmap[0].length) b.accept(heightmap[x][y + 1]);
        return b.build();
    }
}
