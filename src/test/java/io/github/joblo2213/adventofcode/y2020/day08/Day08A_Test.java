package io.github.joblo2213.adventofcode.y2020.day08;

import io.github.joblo2213.adventofcode.y2021.helpers.PuzzleInput;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Day08A_Test {

    @Test
    public void test1() {
        assertEquals(5, new Day08A().run(PuzzleInput.of(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6"
        )));
    }

    @Test
    public void task() throws Exception {
        new Day08A().run();
    }
}

