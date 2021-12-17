package io.github.joblo2213.adventofcode.y2021.day17;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17B_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(112, new Day17B().run(ofMulti("""
                target area: x=20..30, y=-10..-5
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day17B().run();
    }
}
