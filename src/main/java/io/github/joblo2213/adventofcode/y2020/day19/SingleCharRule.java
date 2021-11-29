package io.github.joblo2213.adventofcode.y2020.day19;

import java.util.List;

public class SingleCharRule implements Rule {

    private final char c;

    SingleCharRule(char c) {
        this.c = c;
    }

    @Override
    public List<Match> tryMatch(String string) {
        if (!string.isEmpty() && string.charAt(0) == c) {
            return List.of(new Match(String.valueOf(c), string.substring(1)));
        } else {
            return List.of();
        }
    }
}
