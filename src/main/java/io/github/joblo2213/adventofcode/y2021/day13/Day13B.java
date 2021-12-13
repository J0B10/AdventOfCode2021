package io.github.joblo2213.adventofcode.y2021.day13;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.UnusualTask;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.regex.Pattern.MULTILINE;

public class Day13B extends UnusualTask<String> {

    private static final int MAX_X = 46340; // any x < MAX_X // MAX_X² < Interger.MAX_VALUE

    @Override
    public String runT(PuzzleInput input) {
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", MULTILINE));
        assert inputs.size() == 2;
        Set<Pos> positions = inputs.get(0).map(Day13A::parsePos).collect(Collectors.toSet());
        List<Function<Set<Pos>, Set<Pos>>> functions = inputs.get(1).map(Day13A::parseFoldOperation).toList();
        for (Function<Set<Pos>, Set<Pos>> f : functions) {
            positions = f.apply(positions);
        }
        return resultToString(positions);
    }

    static String resultToString(Set<Pos> pos) {
        TreeSet<Pos> sorted = new TreeSet<>(Comparator.comparingInt((Pos p) -> p.x() + p.y() * MAX_X));
        sorted.addAll(pos);
        Pos prev = new Pos(-1, 0);
        StringBuilder b = new StringBuilder();
        for (Pos p : sorted) {
            if (prev.y() < p.y()) {
                prev = new Pos(0, p.y());
                b.append("\n");
            }
            IntStream.range(prev.x() + 1, p.x()).forEach(i -> b.append(" "));
            b.append("█");
            prev = p;
        }
        b.append("\n");
        return b.toString();
    }
}
