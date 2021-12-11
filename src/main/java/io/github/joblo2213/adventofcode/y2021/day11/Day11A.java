package io.github.joblo2213.adventofcode.y2021.day11;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11A extends Task {

    private final int cycles;

    public Day11A(int cycles) {
        this.cycles = cycles;
    }

    public Day11A() {
        this(100);
    }

    record Pos(int x, int y) {
    }

    @Override
    public long run(PuzzleInput input) throws Exception {
        int[][] octopuses = input.map(s -> s.chars().map(Character::getNumericValue).toArray()).toArray(int[][]::new);
        return IntStream.range(0, cycles).map(i -> step(octopuses)).sum();
    }

    static void print(int[][] octopuses) {
        allPos().forEach(pos -> {
            Consumer<Integer> f = pos.x == 9 ? System.out::println : System.out::print;
            f.accept(octopuses[pos.y][pos.x]);
        });
        System.out.println();
    }

    static int step(int[][] octopuses) {
        allPos().forEach(p -> octopuses[p.y][p.x]++);
        return flash(octopuses);
    }

    static int flash(int[][] octopuses) {
        Stream<Pos> checkFlash = allPos();
        Set<Pos> flashed = new HashSet<>();
        while (true) {
            List<Pos> newFlashes = checkFlash
                    .filter(p -> octopuses[p.y][p.x] > 9)
                    .filter(p -> !flashed.contains(p))
                    .toList();
            if (newFlashes.isEmpty()) break;
            flashed.addAll(newFlashes);
            Map<Pos, Long> adjacent = newFlashes.stream()
                    .flatMap(Day11A::adjacent)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            adjacent.forEach((pos, occurrences) -> octopuses[pos.y][pos.x] += occurrences);
            checkFlash = adjacent.keySet().stream();
        }
        flashed.forEach(p -> octopuses[p.y][p.x] = 0);
        return flashed.size();
    }

    static Stream<Pos> allPos() {
        return IntStream.range(0, 10)
                .mapToObj(y -> IntStream.range(0, 10).mapToObj(x -> new Pos(x, y)))
                .flatMap(Function.identity());
    }

    static Stream<Pos> adjacent(Pos p) {
        return IntStream.rangeClosed(-1, 1).mapToObj(dx -> IntStream.rangeClosed(-1, 1).mapToObj(dy ->
                        new Pos(p.x + dx, p.y + dy)))
                .flatMap(Function.identity())
                .filter(p1 -> !p.equals(p1))
                .filter(p1 -> p1.x >= 0 && p1.y >= 0 && p1.x < 10 && p1.y < 10);
    }
}
