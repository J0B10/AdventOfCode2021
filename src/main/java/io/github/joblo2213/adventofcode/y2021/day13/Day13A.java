package io.github.joblo2213.adventofcode.y2021.day13;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.MULTILINE;

public class Day13A extends Task {

    @Override
    public long run(PuzzleInput input) throws Exception {
        List<PuzzleInput> inputs = input.split(Pattern.compile("\\n\\s*\\n", MULTILINE));
        assert inputs.size() == 2;
        Set<Pos> positions = inputs.get(0).map(Day13A::parsePos).collect(Collectors.toSet());
        Function<Set<Pos>, Set<Pos>> f = inputs.get(1).map(Day13A::parseFoldOperation).findFirst().orElseThrow();
        return f.apply(positions).size();
    }

    static Pos parsePos(String s) {
        List<Integer> vals = PuzzleInput.ofMulti(s, ",").getInts();
        assert vals.size() == 2;
        return new Pos(vals.get(0), vals.get(1));
    }

    static Function<Set<Pos>, Set<Pos>> parseFoldOperation(String s) {
        assert s.startsWith("fold along ");
        String[] parts = s.substring("fold along ".length()).split("=");
        assert parts.length == 2;
        int val = Integer.parseInt(parts[1]);
        return switch (parts[0]) {
            case "x" -> (pos) -> foldVertical(val, pos);
            case "y" -> (pos) -> foldHorizontal(val, pos);
            default -> throw new IllegalArgumentException(s);
        };
    }

    static Set<Pos> foldVertical(int x, Set<Pos> positions) {
        return positions.stream().map(p -> {
            if (p.x() > x) {
                int dx = p.x() - x;
                return new Pos(x - dx, p.y());
            } else {
                return new Pos(p.x(), p.y());
            }
        }).collect(Collectors.toSet());
    }

    static Set<Pos> foldHorizontal(int y, Set<Pos> positions) {
        return positions.stream().map(p -> {
            if (p.y() > y) {
                int dy = p.y() - y;
                return new Pos(p.x(), y - dy);
            } else {
                return new Pos(p.x(), p.y());
            }
        }).collect(Collectors.toSet());
    }
}
