package io.github.joblo2213.adventofcode.y2021.day13;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13A_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(17, new Day13A().run(ofMulti("""
                6,10
                0,14
                9,10
                0,3
                10,4
                4,11
                6,0
                6,12
                4,1
                0,13
                10,12
                3,4
                3,0
                8,4
                1,10
                2,14
                8,10
                9,0
                                
                fold along y=7
                fold along x=5
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day13A().run();
    }
}
