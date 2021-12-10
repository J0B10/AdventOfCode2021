package io.github.joblo2213.adventofcode.y2021.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Optional;
import java.util.Stack;

public class Day10B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        long[] scores = input.lines()
                .map(Day10B::complete)
                .flatMap(Optional::stream)
                .mapToLong(Day10B::score)
                .sorted()
                .toArray();
        return scores[scores.length / 2];
    }

    static Optional<String> complete(String line) {
        Stack<Character> opening = new Stack<>();
        for (char ch : line.toCharArray()) {
            if ("{(<[".contains(String.valueOf(ch))) {
                opening.push(ch);
            } else if (opening.isEmpty()) {
                //incomplete
                return Optional.empty();
            } else if (closingChar(opening.pop()) != ch) {
                //skip corrupted
                return Optional.empty();
            }
        }
        //incomplete or correct
        StringBuilder s = new StringBuilder();
        while (!opening.isEmpty()) {
            s.append(closingChar(opening.pop()));
        }
        return Optional.of(s.toString());
    }

    static char closingChar(char open) {
        return switch (open) {
            case '{' -> '}';
            case '(' -> ')';
            case '<' -> '>';
            case '[' -> ']';
            default -> throw new IllegalArgumentException(String.valueOf(open));
        };
    }

    static int score(char ch) {
        return switch (ch) {
            case ')' -> 1;
            case ']' -> 2;
            case '}' -> 3;
            case '>' -> 4;
            default -> throw new IllegalArgumentException(String.valueOf(ch));
        };
    }

    static long score(String string) {
        long score = 0;
        for (char ch : string.toCharArray()) {
            score = (score * 5) + score(ch);
        }
        return score;
    }

}
