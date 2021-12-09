package io.github.joblo2213.adventofcode.y2021.day09;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day09B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        int[][] heightmap = input.map(s -> s.chars().map(Character::getNumericValue).toArray()).toArray(int[][]::new);
        int h = input.lineCount(), w = input.getLines().get(0).length();
        //find basin centers (lowest points)
        List<Basin> basins = IntStream.range(0, w).mapToObj(y -> IntStream.range(0, h).filter(x -> {
            int val = heightmap[x][y];
            return getNeighbours(x, y, heightmap).map(p -> p.getValue(heightmap)).allMatch(n -> val < n);
        }).mapToObj(x -> new Basin(x, y))).flatMap(Function.identity()).collect(Collectors.toCollection(ArrayList::new));

        //expand basins
        basins.stream().parallel().forEach(b -> b.growArea(heightmap));

        //find 3 biggest basins (skipping identical ones)
        assert basins.size() >= 3;
        basins.sort(Comparator.comparingInt(Basin::getSize).reversed());
        LinkedList<Basin> biggest = new LinkedList<>();
        int i = 0;
        for (Basin b : basins) {
            if (i == 3) break;
            if (!biggest.isEmpty()
                    && biggest.getLast().getSize() == b.getSize()
                    && biggest.getLast().overlap(b)) {
                continue;
            }
            biggest.addLast(b);
            i++;
        }
        return biggest.stream().mapToLong(Basin::getSize).reduce(1L, (a, b) -> a * b);
    }

    static Stream<Pos> getNeighbours(int x, int y, int[][] heightmap) {
        Stream.Builder<Pos> b = Stream.builder();
        if (x > 0) b.accept(new Pos(x - 1, y));
        if (y > 0) b.accept(new Pos(x, y - 1));
        if (x + 1 < heightmap.length) b.accept(new Pos(x + 1, y));
        if (y + 1 < heightmap[0].length) b.accept(new Pos(x, y + 1));
        return b.build();
    }
}
