package io.github.joblo2213.adventofcode.y2021.day03;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03B_Test {


    @Test
    public void test1() throws Exception {
        assertEquals(230, new Day03B().run(PuzzleInput.ofMulti("""
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day03B().run();
    }
}
