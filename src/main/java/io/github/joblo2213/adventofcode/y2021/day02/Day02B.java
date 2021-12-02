package io.github.joblo2213.adventofcode.y2021.day02;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.function.Function;

public class Day02B extends Task {

    record Position(int horizontal, int depth, int aim) {
        Position plus(Position other) {
            return new Position(horizontal + other.horizontal,
                    depth + other.depth,
                    aim + other.aim
            );
        }
    }

    @Override
    public long run(PuzzleInput input) throws Exception {
        Position result = input.map(Day02B::parse).reduce(
                new Position(0, 0, 0),
                (pos, f) -> f.apply(pos),
                Position::plus
        );
        return (long) result.horizontal * result.depth;
    }

    static Function<Position, Position> parse(String s) {
        String[] split = s.split(" ");
        if (split.length != 2) throw new IllegalArgumentException("invalid instruction '" + s + "'");
        int val = Integer.parseInt(split[1]);
        return switch (split[0]) {
            case "forward" -> pos -> pos.plus(new Position(val, pos.aim * val, 0));
            case "down" -> pos -> pos.plus(new Position(0, 0, val));
            case "up" -> pos -> pos.plus(new Position(0, 0, -val));
            default -> throw new IllegalArgumentException("invalid instruction '" + s + "'");
        };
    }
}
