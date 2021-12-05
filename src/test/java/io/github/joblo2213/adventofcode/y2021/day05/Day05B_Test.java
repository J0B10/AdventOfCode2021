package io.github.joblo2213.adventofcode.y2021.day05;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05B_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(12, new Day05B().run(PuzzleInput.ofMulti("""
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2
                """)));
    }

    @Test
    public void test2() {
        assertArrayEquals(new Pos[]{
                new Pos(1, 1),
                new Pos(2, 2),
                new Pos(3, 3)
        }, Line.parse("1,1 -> 3,3").positions().toArray(Pos[]::new));
        assertArrayEquals(new Pos[]{
                new Pos(9, 7),
                new Pos(8, 8),
                new Pos(7, 9)
        }, Line.parse("9,7 -> 7,9").positions().toArray(Pos[]::new));
    }

    @Test
    public void task() throws Exception {
        new Day05B().run();
    }
}
