package io.github.joblo2213.adventofcode.y2021.day11;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11A_Test {

    @Test
    public void test1() throws Exception {
        PuzzleInput input = PuzzleInput.ofMulti("""
                5483143223
                2745854711
                5264556173
                6141336146
                6357385478
                4167524645
                2176841721
                6882881134
                4846848554
                5283751526
                """);
        assertEquals(204, new Day11A(10).run(input));
        assertEquals(1656, new Day11A().run(input));
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day11A().run();
    }
}
