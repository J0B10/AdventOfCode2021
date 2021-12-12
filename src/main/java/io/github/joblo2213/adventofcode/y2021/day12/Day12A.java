package io.github.joblo2213.adventofcode.y2021.day12;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

import java.util.*;

public class Day12A extends Task {

    @Override
    public long run(PuzzleInput input) throws Exception {
        return paths(parseCaves(input), new HashSet<>());
    }

    static Cave parseCaves(PuzzleInput input) {
        Map<String, Cave> caveMap = new HashMap<>();
        for (String line : input) {
            Cave[] caves = Arrays.stream(line.split("-"))
                    .map(s -> Optional.ofNullable(caveMap.get(s)).orElseGet(() -> {
                        Cave c = new Cave(s);
                        caveMap.put(s, c);
                        return c;
                    })).toArray(Cave[]::new);
            assert caves.length == 2;
            caves[0].link(caves[1]);
        }
        //tracing the paths in reverse order (starting at end, going to start)
        //is faster as some wrong paths are not even tried
        return Optional.ofNullable(caveMap.get("end")).orElseThrow();
    }

    static int paths(Cave from, Set<Cave> blacklist) {
        if (from.getLabel().equals("start")) return 1;
        List<Cave> next = from.linked().filter(cave -> !blacklist.contains(cave)).toList();
        if (next.isEmpty()) return 0;
        int totalPaths = 0;
        for (Cave c : next) {
            switch (c.getLabel()) {
                case "end":
                    continue;
                case "start":
                    totalPaths += 1;
                    continue;
            }
            if (c.isSmall()) blacklist.add(c);
            int paths = paths(c, blacklist);
            blacklist.remove(c);
            totalPaths += paths;
        }
        return totalPaths;
    }
}
