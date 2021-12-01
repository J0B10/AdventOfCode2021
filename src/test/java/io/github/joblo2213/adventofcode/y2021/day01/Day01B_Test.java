package io.github.joblo2213.adventofcode.y2021.day01;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class Day01B_Test {


    @Test
    public void test1() {
        assertEquals(5, new Day01B().run(PuzzleInput.ofMulti("""
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """)));
    }


    @Test
    public void task() throws Exception {
        new Day01B().run();
    }
}
