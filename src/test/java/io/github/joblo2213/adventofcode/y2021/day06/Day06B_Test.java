package io.github.joblo2213.adventofcode.y2021.day06;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06B_Test {

    @Test
    public void test1() throws Exception {
        //Test with example for part 1
        PuzzleInput input = ofMulti("""
                3,4,3,1,2
                """);
        assertEquals(5, new Day06B(1).run(input));
        assertEquals(6, new Day06B(2).run(input));
        assertEquals(7, new Day06B(3).run(input));
        assertEquals(9, new Day06B(4).run(input));
        assertEquals(10, new Day06B(5).run(input));
        //....
        assertEquals(10, new Day06B(8).run(input));
        assertEquals(11, new Day06B(9).run(input));
        //...
        assertEquals(26, new Day06B(18).run(input));
        //...
        assertEquals(5934, new Day06B(80).run(input));
    }

    @Test
    public void test2() throws Exception {
        //Test with example for part 2
        assertEquals(26984457539L, new Day06B().run(ofMulti("""
                3,4,3,1,2
                """)));
    }

    @Test
    public void task() throws Exception {
        new Day06B().run();
    }
}
