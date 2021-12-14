package io.github.joblo2213.adventofcode.y2021.day14;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;
import io.github.joblo2213.adventofcode.y2021.helpers.Tuple;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.regex.Pattern.MULTILINE;

public class Day14B extends Task {

    private final int steps;

    /**
     * Holds the pairs of characters that match a rule, so that each rule can just be referenced using a single byte.
     * The byte defines the index of each rule in the array.
     */
    private String[] pairs;

    /**
     * Holds with which two pairs a pair will be replaced when the rule is applied.
     * Indices are identical to those of {@link #pairs}.
     */
    private Replacement[] replacements;

    public Day14B(int steps) {
        this.steps = steps;
    }

    public Day14B() {
        this(40);
    }

    record Replacement(byte x1, byte x2) {
    }

    record State(byte pair, int stepsLeft) {
    }

    @Override
    public long run(PuzzleInput input) throws Exception {

        //input parsing
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", MULTILINE));
        assert inputs.size() == 2;
        parseRules(inputs.get(1));
        String template = inputs.get(0).toString();

        //count occurrences of each character
        Map<Character, Long> occurrences = occurrences(pairs(template));
        char first = template.charAt(0);
        add(occurrences, first, 1L); //first char is it was ignored by occurrences()

        //determine least and most used character
        List<Entry<Character, Long>> occurrencesL = occurrences.entrySet().stream()
                .sorted(Comparator.comparingLong(Entry::getValue))
                .toList();
        long least = occurrencesL.get(0).getValue();
        long most = occurrencesL.get(occurrencesL.size() - 1).getValue();
        return most - least;
    }

    static void add(Map<Character, Long> map, char key, long add) {
        map.put(key, map.getOrDefault(key, 0L) + add);
    }

    void parseRules(PuzzleInput input) {
        pairs = input.map(s -> s.substring(0, s.indexOf(" "))).toArray(String[]::new);
        assert pairs.length < Byte.MAX_VALUE;
        replacements = new Replacement[pairs.length];
        byte i = 0;
        for (String line : input) {
            String pair = pairs[i];
            String rpl = line.substring(line.length() - 1);
            byte x1 = -1, x2 = -1;
            //find the two replacements for this rule in the pairs array:
            for (byte b = 0; b < pairs.length && (x1 == -1 || x2 == -1); b++) {
                if (pairs[b].equals(pair.charAt(0) + rpl)) x1 = b;
                if (pairs[b].equals(rpl + pair.charAt(1))) x2 = b;
            }
            if (x1 == -1) throw new RuntimeException("Pair " + pair.charAt(0) + rpl + " not found");
            else if (x2 == -1) throw new RuntimeException("Pair " + rpl + pair.charAt(1) + " not found");
            else replacements[i++] = new Replacement(x1, x2);
        }
    }

    Stream<Byte> pairs(String s) {
        Map<String, Byte> pairs = IntStream.range(0, this.pairs.length)
                .mapToObj(i -> new Tuple<>(this.pairs[i], (byte) i))
                .collect(Collectors.toMap(Tuple::first, Tuple::second));
        return IntStream.range(0, s.length() - 1)
                .mapToObj(i -> s.substring(i, i + 2))
                .map(p -> Optional.ofNullable(pairs.get(p)).orElseThrow());
    }

    Map<Character, Long> occurrences(Stream<Byte> pairs) {
        Map<State, Map<Character, Long>> map = new HashMap<>();
        Map<Character, Long> occurrences = new HashMap<>();
        pairs.forEach(b -> occurrences(new State(b, steps), map).forEach((k, v) -> add(occurrences, k, v)));
        return occurrences;
    }

    private Map<Character, Long> occurrences(State state, Map<State, Map<Character, Long>> map) {
        //results for all states are kept in memory so that they can be reused.
        //This also prevents long recursive chains.
        if (map.containsKey(state)) return map.get(state);

        Replacement r = replacements[state.pair];
        byte pair1 = r.x1, pair2 = r.x2;
        final Map<Character, Long> m = new HashMap<>();
        if (state.stepsLeft == 1) {
            //to prevent duplicates from overlapping pairs
            //the first char of all polymers will always be ignored and added later (see line 55)
            char ch1 = pairs[pair1].charAt(1), ch2 = pairs[pair2].charAt(1);
            add(m, ch1, 1L);
            add(m, ch2, 1L);
        } else {
            occurrences(new State(pair1, state.stepsLeft - 1), map).forEach((k, v) -> add(m, k, v));
            occurrences(new State(pair2, state.stepsLeft - 1), map).forEach((k, v) -> add(m, k, v));
        }
        map.put(state, m);
        return m;
    }

    @Override
    public String toString() {
        //helps to validate the parsing of rules when debugging
        if (pairs == null) return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < pairs.length; i++) {
            b.append(pairs[i]).append(" -> ");
            b.append(pairs[replacements[i].x1].charAt(1));
            b.append("\n");
        }
        return b.toString();
    }
}
