package io.github.joblo2213.adventofcode.y2020.day19;

import java.util.ArrayList;
import java.util.List;

public class MultiListRule implements Rule {

    private final ListRule either;
    private final ListRule or;

    MultiListRule(ListRule either, ListRule or) {
        this.either = either;
        this.or = or;
    }

    @Override
    public List<Match> tryMatch(String string) {
        List<Match> matches = new ArrayList<>();
        matches.addAll(either.tryMatch(string));
        matches.addAll(or.tryMatch(string));
        return matches;
    }

    @Override
    public String toString() {
        return either + " | " + or;
    }
}
