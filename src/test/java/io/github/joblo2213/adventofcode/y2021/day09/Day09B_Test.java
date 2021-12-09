package io.github.joblo2213.adventofcode.y2021.day09;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09B_Test {
    @Test
    public void test1() throws Exception {
        assertEquals(1134, new Day09B().run(ofMulti("""
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day09B().run();
    }
}
