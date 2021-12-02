package io.github.joblo2213.adventofcode.y2021.day02;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day02A extends Task {

    record Position(int horizontal, int depth) {
        Position plus(Position other) {
            return new Position(horizontal + other.horizontal, depth + other.depth);
        }
    }

    @Override
    public long run(PuzzleInput input) throws Exception {
        Position result = input.map(Day02A::parse).reduce(new Position(0, 0), Position::plus);
        return (long) result.horizontal * result.depth;
    }

    static Position parse(String s) {
        String[] split = s.split(" ");
        if (split.length != 2) throw new IllegalArgumentException("invalid instruction '" + s + "'");
        int val = Integer.parseInt(split[1]);
        return switch (split[0]) {
            case "forward" -> new Position(val, 0);
            case "down" -> new Position(0, val);
            case "up" -> new Position(0, -val);
            default -> throw new IllegalArgumentException("invalid instruction '" + s + "'");
        };
    }
}
