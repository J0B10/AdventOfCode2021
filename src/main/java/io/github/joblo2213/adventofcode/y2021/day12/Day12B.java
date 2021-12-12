package io.github.joblo2213.adventofcode.y2021.day12;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.Arrays;

import static io.github.joblo2213.adventofcode.y2021.day12.Day12A.parseCaves;

public class Day12B extends Task {

    @Override
    public long run(PuzzleInput input) throws Exception {
        return paths(parseCaves(input), new Cave[0], true);
    }

    static int paths(Cave from, Cave[] path, boolean canVisitTwice) {
        if (from.getLabel().equals("start")) return 1;
        final boolean canVisitTwiceF;
        if (from.isSmall()) {
            long previousVisits = Arrays.stream(path).filter(from::equals).count();
            if (previousVisits == 0) {
                canVisitTwiceF = canVisitTwice;
            } else if (previousVisits < 2 && canVisitTwice) {
                canVisitTwiceF = false;
            } else {
                return 0;
            }
        } else {
            canVisitTwiceF = canVisitTwice;
        }
        return from.linked().mapToInt(c -> {
            if (c.getLabel().equals("end")) return 0;
            Cave[] newpath = Arrays.copyOf(path, path.length + 1);
            newpath[newpath.length - 1] = from;
            return paths(c, newpath, canVisitTwiceF);
        }).sum();
    }
}
