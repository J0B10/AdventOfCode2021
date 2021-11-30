package io.github.joblo2213.adventofcode.y2019.day10;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10A_Test {

    @Test
    public void test1() {
        Day10A.Result result = new Day10A().findCenter(PuzzleInput.ofMulti("""
                .#..#
                .....
                #####
                ....#
                ...##
                """));
        assertEquals(new Asteroid(3, 4), result.center());
        assertEquals(8, result.visible());
    }


    @Test
    public void task() throws Exception {
        new Day10A().run();
    }
}
