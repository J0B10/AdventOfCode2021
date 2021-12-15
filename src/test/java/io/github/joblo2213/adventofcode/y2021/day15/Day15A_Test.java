package io.github.joblo2213.adventofcode.y2021.day15;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15A_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(40, new Day15A().run(ofMulti("""
                1163751742
                1381373672
                2136511328
                3694931569
                7463417111
                1319128137
                1359912421
                3125421639
                1293138521
                2311944581
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day15A().run();
    }
}
