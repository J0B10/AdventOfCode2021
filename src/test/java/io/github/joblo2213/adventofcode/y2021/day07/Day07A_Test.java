package io.github.joblo2213.adventofcode.y2021.day07;

import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07A_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(37, new Day07A().run(ofMulti("""
                16,1,2,0,4,2,7,1,2,14
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day07A().run();
    }
}
