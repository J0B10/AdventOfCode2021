package io.github.joblo2213.adventofcode.y2021.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Stack;
import java.util.stream.Stream;

public class Day10A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        return input.lines().flatMap(Day10A::firstIllegalChar).mapToLong(Day10A::score).sum();
    }

    static Stream<Character> firstIllegalChar(String line) {
        Stack<Character> opening = new Stack<>();
        for (char ch : line.toCharArray()) {
            if ("{(<[".contains(String.valueOf(ch))) {
                opening.push(ch);
            } else if (opening.isEmpty()) {
                //incomplete
                return Stream.empty();
            } else if (!match(opening.pop(), ch)) {
                return Stream.of(ch);
            }
        }
        //incomplete or correct
        return Stream.empty();
    }

    static boolean match(char open, char close) {
        return switch (open) {
            case '{' -> '}';
            case '(' -> ')';
            case '<' -> '>';
            case '[' -> ']';
            default -> throw new IllegalArgumentException(open + "" + close);
        } == close;
    }

    static int score(char ch) {
        return switch (ch) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new IllegalArgumentException(String.valueOf(ch));
        };
    }

}
