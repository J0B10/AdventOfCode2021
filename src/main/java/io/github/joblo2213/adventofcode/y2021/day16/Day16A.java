package io.github.joblo2213.adventofcode.y2021.day16;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import io.github.joblo2213.adventofcode.y2021.helpers.Task;

public class Day16A extends Task {
    @Override
    public long run(PuzzleInput input) throws Exception {
        return Packet.parse(input.toString().strip()).allPackets().mapToInt(p -> p.version).sum();
    }
}
