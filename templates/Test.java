package io.github.joblo2213.adventofcode.y2021.day${day2D};

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

public class Day${day2D}${ch}_Test {

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true", disabledReason = "Disabled on GH Actions")
    public void task() throws Exception {
        new Day${day2D}${ch}().run();
    }
}
