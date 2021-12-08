package io.github.joblo2213.adventofcode.y2021.day08;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day08B extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        return input.map(line -> {
            String[] split = line.split("\\|");
            assert split.length == 2;
            final Map<Character, Character> wireMap = parseWireMap(split[0]);
            return PuzzleInput.ofMulti(split[1].trim(), " ").lines()
                    .mapToInt(s -> parseSignal(wireMap, s))
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining());
        }).mapToInt(Integer::parseInt).sum();
    }

    static Map<Character, Character> parseWireMap(String instruction) {
        int i;
        if ((i = instruction.indexOf(" |")) != -1) instruction = instruction.substring(0, i);
        List<String> patterns = Arrays.asList(instruction.split(" "));
        patterns.sort(Comparator.comparingInt(String::length));
        String four = patterns.get(2);
        assert four.length() == 4;
        Map<Character, Character> wireMap = new HashMap<>();
        Map<Character, Long> occurrences = patterns.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Character, Long> entry : occurrences.entrySet()) {
            char ch = switch (entry.getValue().intValue()) {
                case 6 -> 'b';
                case 4 -> 'e';
                case 9 -> 'f';
                case 7 -> four.contains(entry.getKey().toString()) ? 'd' : 'g';
                case 8 -> four.contains(entry.getKey().toString()) ? 'c' : 'a';
                default -> throw new RuntimeException(entry.getKey() + ": " + entry.getValue());
            };
            wireMap.put(ch, entry.getKey());
        }
        return wireMap;
    }

    static int parseSignal(Map<Character, Character> wireMap, String signal) {
        return switch (signal.length()) {
            case 2 -> 1;
            case 3 -> 7;
            case 4 -> 4;
            case 7 -> 8;
            case 6 -> {
                if (!signal.contains(wireMap.get('d').toString())) {
                    yield 0;
                } else if (signal.contains(wireMap.get('e').toString())) {
                    yield 6;
                } else {
                    yield 9;
                }
            }
            case 5 -> {
                if (signal.contains(wireMap.get('b').toString())) {
                    yield 5;
                } else if (signal.contains(wireMap.get('e').toString())) {
                    yield 2;
                } else {
                    yield 3;
                }
            }
            default -> throw new RuntimeException(signal);
        };
    }
}
