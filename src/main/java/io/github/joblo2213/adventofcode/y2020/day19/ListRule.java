package io.github.joblo2213.adventofcode.y2020.day19;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListRule implements Rule {

    private final Alphabet alphabet;
    private final int[] rules;

    ListRule(Alphabet alphabet, int... rules) {
        this.alphabet = alphabet;
        this.rules = rules;
    }

    @Override
    public List<Match> tryMatch(String string) {
        List<Match> matches = List.of(new Match("", string));
        for (int i : rules) {
            final Rule r = alphabet.getRule(i);
            matches = matches.stream().map(r::tryMatch).flatMap(List::stream).collect(Collectors.toList());

        }
        return matches;
    }

    @Override
    public String toString() {
        return Arrays.stream(rules).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}
