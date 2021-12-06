package io.github.joblo2213.adventofcode.y2021.day06;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput.ofMulti;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06A_Test {

    @Test
    public void test1() throws Exception {
        assertEquals(5934, new Day06A().run(ofMulti("""
                3,4,3,1,2
                """)));
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day06A().run();
    }
}
